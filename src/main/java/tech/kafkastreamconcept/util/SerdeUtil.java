package tech.kafkastreamconcept.util;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import tech.kafkastreamconcept.model.OddOrEvenEvent;

public class SerdeUtil {

    public static Serde<OddOrEvenEvent> oddOrEvenEventSerde() {
        return Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(OddOrEvenEvent.class));
    }
}
