package org.exampleVariant3.chapter6.manager;

import org.exampleVariant3.chapter6.model.Candy;
import org.exampleVariant3.chapter6.model.Chocolate;
import org.exampleVariant3.chapter6.model.Lollipop;
import org.exampleVariant3.chapter6.model.Toffee;

import java.util.ArrayList;
import java.util.List;

public class CandyManagerImpl implements CandyManager {
    private List<Candy> candies = new ArrayList<>();

    @Override
    public void produceCandyBatch(String type, int quantity) {
        boolean candyExists = false;
        for (Candy candy : candies) {
            if (candy.getName().equalsIgnoreCase(type)) {
                candyExists = true;
                break;
            }
        }

        if (candyExists) {
            System.out.printf("Произведено %d сладости типа %s.%n", quantity, type);
        } else {
            System.out.println("Сладость типа " + type + " не найдена в производстве.");
        }
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
        boolean addIngredient = false;

        for (Candy candy : candies) {
            if (!candy.getIngredients().contains(ingredient)) {
                candy.addIngredient(ingredient);
                addIngredient = true;
            }
        }

        if (addIngredient) {
            System.out.println("Ингредиент добавлен: " + ingredient);
        } else {
            System.out.println("Ингредиент уже содержится во всех сладостях.");
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
}
