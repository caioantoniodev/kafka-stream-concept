package tech.kafkastreamconcept.processing;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;
import tech.kafkastreamconcept.model.OddOrEvenEvent;

@Component
public class TopologyStream {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopologyStream.class);

    private final String inPlayOddOrEvenTopic;
    private final String outOddTopic;
    private final String outEvenTopic;

    TopologyStream(@Value("${topics.play-odd-or-even-topic}") String inPlayOddOrEvenTopic,
                   @Value("${topics.odd-topic}") String outOddTopic,
                   @Value("${topics.even-topic}") String outEvenTopic){

        this.inPlayOddOrEvenTopic = inPlayOddOrEvenTopic;
        this.outOddTopic = outOddTopic;
        this.outEvenTopic = outEvenTopic;
    }

    private Serde<OddOrEvenEvent> odsOrEvensEventSerde() {
        return Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(OddOrEvenEvent.class));
    }

    @Autowired
    void consumeEvent(@Autowired StreamsBuilder builder) {
        builder.stream(inPlayOddOrEvenTopic, Consumed.with(Serdes.String(), odsOrEvensEventSerde()))
                .peek((k, message) -> LOGGER.info("Received message [{}]", message))
                .split()
                .branch(new IsOddNumberPredicate(), Branched.withConsumer(baseStream -> {
                    baseStream
                            .peek((k, message) -> LOGGER.info("The number [{}] is odd", message.getNumber()))
                            .to(outOddTopic, Produced.with(Serdes.String(), odsOrEvensEventSerde()));
                }))
                .branch(new IsEvenNumberPredicate(), Branched.withConsumer(baseStream -> {
                    baseStream
                            .peek((k, message) -> LOGGER.info("The number [{}] is even", message.getNumber()))
                            .to(outEvenTopic, Produced.with(Serdes.String(), odsOrEvensEventSerde()));
                }))
                .noDefaultBranch();
    }
}
