package tech.kafkastreamconcept.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.kafkastreamconcept.model.OddOrEvenEvent;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class TopologyStreamTest {

    private Topology topology;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String playOddOrEvenTopicName = "kafka-stream-concept.odd-or-even.play.v1.dev";
    private final String oddTopicName = "kafka-stream-concept.odd.number.v1.dev";
    private final String evenTopicName = "kafka-stream-concept.even.number.v1.dev";

    @BeforeEach
    void setUp() {
        var topologyStream = new TopologyStream(playOddOrEvenTopicName, oddTopicName, evenTopicName);
        var streamsBuilder = new StreamsBuilder();
        topologyStream.buildPipeline(streamsBuilder);
        topology = streamsBuilder.build();
    }

    @Test
    void testOddNumber() throws JsonProcessingException {
        try (var topologyTestDriver = new TopologyTestDriver(topology, new Properties())) {
           var playOddOrEvenTopic = topologyTestDriver
                    .createInputTopic(playOddOrEvenTopicName, new StringSerializer(), new StringSerializer());

            var oddTopic = topologyTestDriver
                    .createOutputTopic(oddTopicName, new StringDeserializer(), new StringDeserializer());

            var inputEvent = new OddOrEvenEvent();
            inputEvent.setNumber(3d);

            playOddOrEvenTopic.pipeInput("key", objectMapper.writeValueAsString(inputEvent));

            var response = objectMapper.readValue(oddTopic.readValue(), OddOrEvenEvent.class);
            assertEquals(response.getNumber(), inputEvent.getNumber());
        }
    }

    @Test
    void testEvenNumber() throws JsonProcessingException {
        try (var topologyTestDriver = new TopologyTestDriver(topology, new Properties())) {
            var playOddOrEvenTopic = topologyTestDriver
                    .createInputTopic(playOddOrEvenTopicName, new StringSerializer(), new StringSerializer());

            var evenTopic = topologyTestDriver
                    .createOutputTopic(evenTopicName, new StringDeserializer(), new StringDeserializer());

            var inputEvent = new OddOrEvenEvent();
            inputEvent.setNumber(4d);

            playOddOrEvenTopic.pipeInput("key", objectMapper.writeValueAsString(inputEvent));

            var response = objectMapper.readValue(evenTopic.readValue(), OddOrEvenEvent.class);
            assertEquals(response.getNumber(), inputEvent.getNumber());
        }
    }
}
