package main.najah.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;

@DisplayName("TestRecipeBook")
public class TestRecipeBook {

    @DisplayName("getRecipes returns initialized array")
    @Test
    void getRecipes_valid() {
        RecipeBook recipeBook = new RecipeBook();
        assertNotNull(recipeBook.getRecipes());
        assertEquals(4, recipeBook.getRecipes().length);
    }

    @DisplayName("addRecipe supports valid add and duplicate rejection")
    @Test
    void addRecipe_validAndInvalid() {
        RecipeBook recipeBook = new RecipeBook();
        Recipe recipe = new Recipe();
        recipe.setName("Mocha");

        assertTrue(recipeBook.addRecipe(recipe));
        assertFalse(recipeBook.addRecipe(recipe));
    }

    @DisplayName("addRecipe rejects second recipe with same name (different instance)")
    @Test
    void addRecipe_duplicateNameDifferentInstance_returnsFalse() {
        RecipeBook book = new RecipeBook();
        Recipe first = new Recipe();
        first.setName("Dup");
        Recipe second = new Recipe();
        second.setName("Dup");

        assertTrue(book.addRecipe(first));
        assertFalse(book.addRecipe(second));
    }

    @DisplayName("deleteRecipe supports existing and empty slot")
    @Test
    void deleteRecipe_validAndInvalid() {
        RecipeBook recipeBook = new RecipeBook();
        Recipe recipe = new Recipe();
        recipe.setName("Latte");
        recipeBook.addRecipe(recipe);

        assertEquals("Latte", recipeBook.deleteRecipe(0));
        assertNull(recipeBook.deleteRecipe(1));
    }

    @DisplayName("editRecipe supports existing and empty slot")
    @Test
    void editRecipe_validAndInvalid() {
        RecipeBook recipeBook = new RecipeBook();
        Recipe oldRecipe = new Recipe();
        oldRecipe.setName("Old");
        recipeBook.addRecipe(oldRecipe);

        Recipe newRecipe = new Recipe();
        newRecipe.setName("New");

        assertEquals("Old", recipeBook.editRecipe(0, newRecipe));
        assertNull(recipeBook.editRecipe(1, new Recipe()));
    }

    @DisplayName("addRecipe returns false when book is full")
    @Test
    void addRecipe_whenFull_returnsFalse() {
        RecipeBook recipeBook = new RecipeBook();

        for (int i = 0; i < 4; i++) {
            Recipe recipe = new Recipe();
            recipe.setName("R" + i);
            assertTrue(recipeBook.addRecipe(recipe));
        }

        Recipe extra = new Recipe();
        extra.setName("Extra");
        assertFalse(recipeBook.addRecipe(extra));
    }

    @DisplayName("deleteRecipe returns empty recipe name after delete")
    @Test
    void deleteRecipe_afterDelete_returnsEmptyName() {
        RecipeBook recipeBook = new RecipeBook();
        Recipe recipe = new Recipe();
        recipe.setName("Espresso");
        recipeBook.addRecipe(recipe);

        assertEquals("Espresso", recipeBook.deleteRecipe(0));
        assertEquals("", recipeBook.deleteRecipe(0));
    }
}
