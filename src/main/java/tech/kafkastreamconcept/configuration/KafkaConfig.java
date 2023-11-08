package tech.kafkastreamconcept.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Autowired
    private Topology streamsTopology;

    @Bean
    KafkaStreams kStreamsConfigs(final KafkaProperties kafkaProperties) {
        var props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-stream-concept");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 1);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName());

        var describe = streamsTopology.describe();

        final var topology = new KafkaStreams(streamsTopology, props);
        topology.start();

        return topology;
    }

}
