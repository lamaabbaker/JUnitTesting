package main.najah.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import main.najah.code.Calculator;

@DisplayName("TestCalculator")
@TestMethodOrder(OrderAnnotation.class)
public class TestCalculator {

    private Calculator calculator;

    @BeforeAll
    static void beforeAll() {
        System.out.println("TestCalculator: setup complete");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("TestCalculator: cleanup complete");
    }

    @BeforeEach
    void beforeEach() {
        calculator = new Calculator();
        System.out.println("TestCalculator: before each test");
    }

    @AfterEach
    void afterEach() {
        System.out.println("TestCalculator: after each test");
    }

    static Stream<Arguments> addData() {
        return Stream.of(
            Arguments.of(new int[] {1, 2, 3}, 6),
            Arguments.of(new int[] {10, -3, 2}, 9),
            Arguments.of(new int[] {}, 0)
        );
    }

    @Order(1)
    @DisplayName("add returns expected sum (parameterized)")
    @ParameterizedTest(name = "add({0}) => {1}")
    @MethodSource("addData")
    void add_validInputs_returnsExpected(int[] numbers, int expected) {
        assertEquals(expected, calculator.add(numbers));
    }

    @Order(2)
    @DisplayName("divide supports valid and invalid denominator")
    @Test
    void divide_validAndInvalid() {
        assertEquals(5, calculator.divide(10, 2));
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }

    @Order(3)
    @DisplayName("divide handles negative numbers")
    @Test
    void divide_negativeNumbers() {
        assertEquals(-3, calculator.divide(-9, 3));
        assertEquals(3, calculator.divide(-9, -3));
    }

    @Order(4)
    @DisplayName("factorial supports valid and invalid input")
    @Test
    void factorial_validAndInvalid() {
        assertEquals(120, calculator.factorial(5));
        assertThrows(IllegalArgumentException.class, () -> calculator.factorial(-1));
    }

    @Order(5)
    @DisplayName("factorial handles base cases")
    @Test
    void factorial_baseCases() {
        assertEquals(1, calculator.factorial(0));
        assertEquals(1, calculator.factorial(1));
    }

    @Order(6)
    @DisplayName("factorial finishes within timeout")
    @Test
    void factorial_timeout() {
        assertTimeout(Duration.ofMillis(100), () -> assertEquals(3628800, calculator.factorial(10)));
    }
}
