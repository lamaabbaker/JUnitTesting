package main.najah.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.najah.code.Recipe;
import main.najah.code.RecipeException;

@DisplayName("TestRecipe")
public class TestRecipe {

    @DisplayName("default constructor initializes fields")
    @Test
    void constructor_defaults() {
        Recipe r = new Recipe();
        assertEquals("", r.getName());
        assertEquals(0, r.getPrice());
        assertEquals(0, r.getAmtCoffee());
        assertEquals(0, r.getAmtMilk());
        assertEquals(0, r.getAmtSugar());
        assertEquals(0, r.getAmtChocolate());
    }

    @DisplayName("setAmtChocolate accepts valid and rejects invalid input")
    @Test
    void setAmtChocolate_validAndInvalid() throws RecipeException {
        Recipe r = new Recipe();
        r.setAmtChocolate("3");
        assertEquals(3, r.getAmtChocolate());
        assertThrows(RecipeException.class, () -> r.setAmtChocolate("x"));
        assertThrows(RecipeException.class, () -> r.setAmtChocolate("-1"));
    }

    @DisplayName("setAmtCoffee accepts valid and rejects invalid input")
    @Test
    void setAmtCoffee_validAndInvalid() throws RecipeException {
        Recipe r = new Recipe();
        r.setAmtCoffee("2");
        assertEquals(2, r.getAmtCoffee());
        assertThrows(RecipeException.class, () -> r.setAmtCoffee("bad"));
        assertThrows(RecipeException.class, () -> r.setAmtCoffee("-5"));
    }

    @DisplayName("setAmtMilk accepts valid and rejects invalid input")
    @Test
    void setAmtMilk_validAndInvalid() throws RecipeException {
        Recipe r = new Recipe();
        r.setAmtMilk("1");
        assertEquals(1, r.getAmtMilk());
        assertThrows(RecipeException.class, () -> r.setAmtMilk("nope"));
        assertThrows(RecipeException.class, () -> r.setAmtMilk("-2"));
    }

    @DisplayName("setAmtSugar accepts valid and rejects invalid input")
    @Test
    void setAmtSugar_validAndInvalid() throws RecipeException {
        Recipe r = new Recipe();
        r.setAmtSugar("0");
        assertEquals(0, r.getAmtSugar());
        assertThrows(RecipeException.class, () -> r.setAmtSugar("1.5"));
        assertThrows(RecipeException.class, () -> r.setAmtSugar("-1"));
    }

    @DisplayName("setPrice accepts valid and rejects invalid input")
    @Test
    void setPrice_validAndInvalid() throws RecipeException {
        Recipe r = new Recipe();
        r.setPrice("50");
        assertEquals(50, r.getPrice());
        assertThrows(RecipeException.class, () -> r.setPrice("abc"));
        assertThrows(RecipeException.class, () -> r.setPrice("-10"));
    }

    @DisplayName("setName updates when non-null and ignores null")
    @Test
    void setName_nonNullAndNull() {
        Recipe r = new Recipe();
        r.setName("Latte");
        assertEquals("Latte", r.getName());
        r.setName(null);
        assertEquals("Latte", r.getName());
    }

    @DisplayName("toString returns name")
    @Test
    void toString_returnsName() {
        Recipe r = new Recipe();
        r.setName("Mocha");
        assertEquals("Mocha", r.toString());
    }

    @DisplayName("equals and hashCode follow name equality")
    @Test
    void equals_and_hashCode() {
        Recipe a = new Recipe();
        a.setName("A");
        Recipe b = new Recipe();
        b.setName("A");
        Recipe c = new Recipe();
        c.setName("B");

        assertTrue(a.equals(a));
        assertFalse(a.equals(null));
        assertFalse(a.equals("not a recipe"));
        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a.hashCode(), c.hashCode());
    }
}
