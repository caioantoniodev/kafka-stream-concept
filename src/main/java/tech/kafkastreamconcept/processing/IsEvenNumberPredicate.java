package tech.kafkastreamconcept.processing;

import org.apache.kafka.streams.kstream.Predicate;
import tech.kafkastreamconcept.model.OddOrEvenEvent;

public class IsEvenNumberPredicate implements Predicate<String, OddOrEvenEvent> {
    @Override
    public boolean test(String key, OddOrEvenEvent oddOrEvenEvent) {
        return (oddOrEvenEvent.getNumber() % 2) == 0;
    }
}
