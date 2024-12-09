package org.exampleVariant3.chapter6.model;

import java.util.List;

public class Toffee extends Candy {
    public Toffee(String name, String manufacturer, double price, List<String> ingredients) {
        super(name, manufacturer, price, ingredients);
    }

    @Override
    public void specialMethod() {
        //количество молока показывает насколько ирис тягучий
        long milkCount = getIngredients().stream().filter(ingredient -> ingredient.equalsIgnoreCase("Молоко")).count();
        if (milkCount > 1) {
            System.out.println("Этот ирис очень тягучий благодаря большому количеству молока.");
        } else {
            System.out.println("Этот ирис умеренно тягучий.");
        }
    }
}
