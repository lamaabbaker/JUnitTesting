package main.najah.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.najah.code.Product;

@DisplayName("TestProduct")
public class TestProduct {

    @DisplayName("constructor accepts valid price and rejects negative price")
    @Test
    void constructor_validAndInvalid() {
        Product product = new Product("Book", 100.0);
        assertEquals("Book", product.getName());
        assertEquals(100.0, product.getPrice());
        assertEquals(0.0, product.getDiscount());
        assertThrows(IllegalArgumentException.class, () -> new Product("Bad", -1.0));
    }

    @DisplayName("applyDiscount accepts valid and rejects invalid discount")
    @Test
    void applyDiscount_validAndInvalid() {
        Product product = new Product("Laptop", 2000.0);
        product.applyDiscount(10.0);
        assertEquals(10.0, product.getDiscount());
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(60.0));
    }

    @DisplayName("getFinalPrice returns expected value after discount")
    @Test
    void getFinalPrice_validCalculation() {
        Product product = new Product("Pen", 20.0);
        product.applyDiscount(25.0);
        assertEquals(15.0, product.getFinalPrice());
    }

    @DisplayName("applyDiscount accepts zero and upper boundary")
    @Test
    void applyDiscount_boundaryValues() {
        Product product = new Product("Phone", 1000.0);
        product.applyDiscount(0.0);
        assertEquals(1000.0, product.getFinalPrice());
        product.applyDiscount(50.0);
        assertEquals(500.0, product.getFinalPrice());
    }

    @DisplayName("applyDiscount rejects below and above boundary")
    @Test
    void applyDiscount_invalidBoundaries() {
        Product product = new Product("Tablet", 300.0);
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(-0.1));
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(50.1));
    }

    @Disabled("Intentional failing test for assignment; fix by asserting 15.0 instead of 14.0")
    @DisplayName("intentional failing test")
    @Test
    void intentionallyFailingTest() {
        Product product = new Product("Pen", 20.0);
        product.applyDiscount(25.0);
        assertEquals(14.0, product.getFinalPrice());
    }
}
