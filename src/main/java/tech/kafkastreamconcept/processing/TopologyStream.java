package tech.kafkastreamconcept.processing;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopologyStream {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopologyStream.class);
    private final String inEventTopic;
    private final String outParkingLotTopic;

    TopologyStream(@Value("${topics.inbound-event}") String inEventTopic,
                   @Value("${topics.parking-lot}") String outParkingLotTopic) {

        this.inEventTopic = inEventTopic;
        this.outParkingLotTopic = outParkingLotTopic;
    }

    @Bean
    public Topology topology() {
        final var builder = new StreamsBuilder();
        this.consumeEvent(builder);
        return builder.build();
    }

    void consumeEvent(StreamsBuilder builder) {
        builder.stream(inEventTopic, Consumed.with(Serdes.String(), Serdes.String()))
                .peek((k, message) -> LOGGER.info("Received message [{}]", message))
                .to(outParkingLotTopic, Produced.with(Serdes.String(), Serdes.String()));
    }
}
