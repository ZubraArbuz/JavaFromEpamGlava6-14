package org.example.chapter6.manager;

import org.example.chapter6.model.Candy;
import org.example.chapter6.model.Chocolate;
import org.example.chapter6.model.Lollipop;
import org.example.chapter6.model.Toffee;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandyManagerImpl implements CandyManager {
    private List<Candy> candies = new ArrayList<>();
    private List<String> ingredients = new ArrayList<>();

    @Override
    public void produceCandyBatch(String type, int quantity) {
        System.out.printf("Произведено %d сладости типа %s.%n", quantity, type);
    }

    @Override
    public void getProductInfoByManufacturer(String manufacturer) {
        System.out.printf("Продукты от %s:%n", manufacturer);
        for (Candy candy : candies) {
            if (candy.getManufacturer().equalsIgnoreCase(manufacturer)) {
                System.out.println(candy);
            }
        }
    }

    @Override
    public void addOrUpdateCandy(String type, String manufacturer, double price, String[] newIngredients) {
        for (Candy candy : candies) {
            if (candy.getName().equalsIgnoreCase(type)) {
                candy.setPrice(price);
                for (String ingredient : newIngredients) {
                    candy.addIngredient(ingredient);
                }
                System.out.println("Сладость обновлена: " + candy);
                return;
            }
        }
        System.out.println("Конфета не найдена. Используйте createNewCandyType чтобы добавить новую сладость.");
    }

    @Override
    public void addNewIngredient(String ingredient) {
        if (!ingredients.contains(ingredient)) {
            ingredients.add(ingredient);
            System.out.println("Ингридиент добавлен: " + ingredient);
        } else {
            System.out.println("Ингридиент уже добавлен.");
        }
    }

    @Override
    public void createNewCandyType(String type, String manufacturer, String[] newIngredients, double price) {
        List<String> ingredientList = List.of(newIngredients);
        Candy candy;
        switch (type.toLowerCase()) {
            case "шоколад":
                candy = new Chocolate(type, manufacturer, price, ingredientList);
                break;
            case "леденец":
                candy = new Lollipop(type, manufacturer, price, ingredientList);
                break;
            case "ирис":
                candy = new Toffee(type, manufacturer, price, ingredientList);
                break;
            default:
                System.out.println("Неверный выбор сладости.");
                return;
        }
        candies.add(candy);
        System.out.println("Сладость создана: " + candy);
    }

    @Override
    public void discontinueCandyType(String type) {
        candies.removeIf(candy -> candy.getName().equalsIgnoreCase(type));
        System.out.println("Тип сладости снят с производства: " + type);
    }

    public List<Candy> getCandies() {
        return candies;
    }
    public List<String> getIngredients() {
        return ingredients;
    }
}
