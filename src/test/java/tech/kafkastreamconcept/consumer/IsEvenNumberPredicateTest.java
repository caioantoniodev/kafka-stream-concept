package tech.kafkastreamconcept.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.kafkastreamconcept.model.OddOrEvenEvent;

public class IsEvenNumberPredicateTest {

    private final IsEvenNumberPredicate isOddNumberPredicate = new IsEvenNumberPredicate();

    @Test
    void shouldBeReturnFalseWhenNumberIsOdd() {
        OddOrEvenEvent event = new OddOrEvenEvent();
        event.setNumber(3d);

        boolean test = isOddNumberPredicate.test("", event);
        Assertions.assertFalse(test);
    }

    @Test
    void shouldBeReturnTrueWhenNumberIsEven() {
        OddOrEvenEvent event = new OddOrEvenEvent();
        event.setNumber(4d);

        boolean test = isOddNumberPredicate.test("", event);
        Assertions.assertTrue(test);
    }
}
