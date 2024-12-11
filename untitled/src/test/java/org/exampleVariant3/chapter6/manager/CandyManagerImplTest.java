package org.exampleVariant3.chapter6.manager;

import org.exampleVariant3.chapter6.model.Candy;
import org.exampleVariant3.chapter6.model.Chocolate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CandyManagerImplTest {

    private CandyManagerImpl candyManager;

    @BeforeEach
    void setUp() {
        candyManager = new CandyManagerImpl();
    }

    @Test
    void testCreateNewCandyType() {
        String type = "шоколад";
        String manufacturer = "Компания A";
        String[] ingredients = {"шоколад", "сахар"};
        double price = 100.0;

        candyManager.createNewCandyType(type, manufacturer, ingredients, price);

        List<Candy> candies = candyManager.getCandies();
        assertEquals(1, candies.size());
        assertTrue(candies.get(0) instanceof Chocolate);
        assertEquals(type, candies.get(0).getName());
        assertEquals(manufacturer, candies.get(0).getManufacturer());
        assertEquals(price, candies.get(0).getPrice());
    }

    @Test
    void testAddOrUpdateCandy() {
        String type = "леденец";
        String manufacturer = "Компания B";
        String[] ingredients = {"сахар", "вода"};
        double price = 50.0;

        candyManager.createNewCandyType(type, manufacturer, ingredients, price);

        String[] newIngredients = {"сахар", "вода", "вишня"};
        candyManager.addOrUpdateCandy(type, manufacturer, 60.0, newIngredients);

        List<Candy> candies = candyManager.getCandies();
        assertEquals(1, candies.size());
        Candy candy = candies.get(0);
        assertEquals(60.0, candy.getPrice());
        assertTrue(candy.getIngredients().contains("вишня"));
    }

    @Test
    void testDiscontinueCandyType() {
        String type = "ирис";
        String manufacturer = "Компания C";
        String[] ingredients = {"сахар", "масло"};
        double price = 120.0;

        candyManager.createNewCandyType(type, manufacturer, ingredients, price);
        candyManager.discontinueCandyType(type);

        List<Candy> candies = candyManager.getCandies();
        assertTrue(candies.isEmpty());
    }

    @Test
    void testAddNewIngredient() {
        candyManager.createNewCandyType("Шоколад", "Россия", new String[]{"Сахар", "Какао"}, 50.0);
        candyManager.createNewCandyType("Леденец", "Китай", new String[]{"Сахар", "Вода"}, 10.0);

        String ingredient = "Молоко";
        candyManager.addNewIngredient("Леденец", ingredient);

        boolean isIngredientAddedToLollipop = false;
        boolean isIngredientAddedToChocolate = false;
        for (Candy candy : candyManager.getCandies()) {
            if (candy.getName().equalsIgnoreCase("Леденец")) {
                isIngredientAddedToLollipop = candy.getIngredients().contains(ingredient);
            }
            if (candy.getName().equalsIgnoreCase("Шоколад")) {
                isIngredientAddedToChocolate = candy.getIngredients().contains(ingredient);
            }
        }

        assertTrue(isIngredientAddedToLollipop, "Ингредиент не добавлен в леденец");
        assertFalse(isIngredientAddedToChocolate, "Ингредиент добавлен в шоколад, хотя не должен был");

        candyManager.addNewIngredient("Леденец", ingredient);

        for (Candy candy : candyManager.getCandies()) {
            if (candy.getName().equalsIgnoreCase("Леденец")) {
                long ingredientCount = candy.getIngredients().stream()
                        .filter(i -> i.equals(ingredient))
                        .count();
                assertEquals(1, ingredientCount, "Ингредиент добавлен повторно в леденец");
            }
        }

        String newIngredient = "Молоко";
        candyManager.addNewIngredient("Шоколад", newIngredient);

        boolean isNewIngredientAddedToChocolate = false;
        for (Candy candy : candyManager.getCandies()) {
            if (candy.getName().equalsIgnoreCase("Шоколад")) {
                isNewIngredientAddedToChocolate = candy.getIngredients().contains(newIngredient);
            }
        }

        assertTrue(isNewIngredientAddedToChocolate, "Ингредиент не добавлен в шоколад");
    }


    @Test
    void testGetProductInfoByManufacturer() {
        String manufacturer = "Компания A";
        String type = "шоколад";
        String[] ingredients = {"шоколад", "сахар"};
        double price = 100.0;

        candyManager.createNewCandyType(type, manufacturer, ingredients, price);
        candyManager.getProductInfoByManufacturer(manufacturer);
    }

}
