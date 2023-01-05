package com.pawelpluta.day003;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParameterizedTest {

    private final DummyCalculator calculator = new DummyCalculator();


    private static Stream<Arguments> dataForCorrectSumCalculation() {
        return Stream.of(
                Arguments.of("two positive numbers can be added", 3, 2, 5),
                Arguments.of("negative and positive numbers can be added", -3, 2, -1),
                Arguments.of("two negative numbers can be added", -3, -2, -5)
        );
    }

    private static Stream<Arguments> dataForException() {
        return Stream.of(
                Arguments.of("null as first number is unsupported", null, ONE),
                Arguments.of("null as second number is unsupported", ONE, null),
                Arguments.of("null as both numbers is unsupported", null, null)
        );
    }

    @org.junit.jupiter.params.ParameterizedTest(name = "{index}. {0}")
    @MethodSource("dataForCorrectSumCalculation")
    void shouldCalculateSumCorrectly(String name, int num1, int num2, int expectedResult) {
        assertEquals(valueOf(expectedResult), calculator.sum(valueOf(num1), valueOf(num2)));
    }

    @org.junit.jupiter.params.ParameterizedTest(name = "{index}. {0}")
    @MethodSource("dataForException")
    void shouldThrowException(String name, BigDecimal num1, BigDecimal num2) {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.sum(num1, num2));
    }
}
