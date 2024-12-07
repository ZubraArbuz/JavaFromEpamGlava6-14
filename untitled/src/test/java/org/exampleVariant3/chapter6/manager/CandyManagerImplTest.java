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

        // Обновление конфеты
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
        String ingredient = "молоко";

        candyManager.addNewIngredient(ingredient);
        assertTrue(candyManager.getIngredients().contains(ingredient));

        // Попытка добавить тот же ингредиент
        candyManager.addNewIngredient(ingredient);
        assertEquals(1, candyManager.getIngredients().size());
    }

    @Test
    void testGetProductInfoByManufacturer() {
        String manufacturer = "Компания A";
        String type = "шоколад";
        String[] ingredients = {"шоколад", "сахар"};
        double price = 100.0;

        candyManager.createNewCandyType(type, manufacturer, ingredients, price);

        // Проверяем вывод по производителю
        candyManager.getProductInfoByManufacturer(manufacturer);
    }

}
