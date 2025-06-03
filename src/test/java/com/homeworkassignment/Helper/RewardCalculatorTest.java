package com.homeworkassignment.Helper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RewardCalculatorTest {

    @Test
    void calculatePoints_under50() {
        assertEquals(0, RewardCalculator.calculatePoints(49.99));
    }

    @Test
    void calculatePoints_between50And100() {
        assertEquals(10, RewardCalculator.calculatePoints(60.0));
        assertEquals(50, RewardCalculator.calculatePoints(100.0));
    }

    @Test
    void calculatePoints_over100() {
        assertEquals(90, RewardCalculator.calculatePoints(120.0));
        assertEquals(52, RewardCalculator.calculatePoints(101.0));
    }

    @Test
    void calculatePoints_negativeAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> RewardCalculator.calculatePoints(-10.0));
    }

    // Parameterized tests to cover a range of inputs in a single concise test method

    @ParameterizedTest
    @CsvSource({
            "30.0, 0",
            "49.99, 0",
            "50.0, 0",
            "60.0, 10",
            "100.0, 50",
            "101.0, 52",
            "120.0, 90",
            "150.99, 150"
    })
    void calculatePoints_parameterized(double amount, int expectedPoints) {
        assertEquals(expectedPoints, RewardCalculator.calculatePoints(amount));
    }

    @ParameterizedTest
    @CsvSource({
            "-0.01",
            "-10.0"
    })
    void calculatePoints_negative_amount_parameterized(double amount) {
        assertThrows(IllegalArgumentException.class,
                () -> RewardCalculator.calculatePoints(amount));
    }
}
