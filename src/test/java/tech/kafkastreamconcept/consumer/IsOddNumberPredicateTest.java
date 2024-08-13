package tech.kafkastreamconcept.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.kafkastreamconcept.model.OddOrEvenEvent;

public class IsOddNumberPredicateTest {

    private final IsOddNumberPredicate isOddNumberPredicate = new IsOddNumberPredicate();

    @Test
    void shouldBeReturnTrueWhenNumberIsOdd() {
        OddOrEvenEvent event = new OddOrEvenEvent();
        event.setNumber(3d);

        boolean test = isOddNumberPredicate.test("", event);
        Assertions.assertTrue(test);
    }

    @Test
    void shouldBeReturnFalseWhenNumberIsEven() {
        OddOrEvenEvent event = new OddOrEvenEvent();
        event.setNumber(4d);

        boolean test = isOddNumberPredicate.test("", event);
        Assertions.assertFalse(test);
    }
}
