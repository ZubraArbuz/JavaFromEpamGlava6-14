package org.exampleVariant3.chapter6.model;

import java.util.List;

public class Lollipop extends Candy {
    public Lollipop(String name, String manufacturer, double price, List<String> ingredients) {
        super(name, manufacturer, price, ingredients);
    }

    @Override
    public void specialMethod() {
        //количество сахара показывает насколько леденец сладкий
        long sugarCount = getIngredients().stream().filter(ingredient -> ingredient.equalsIgnoreCase("Сахар")).count();
        if (sugarCount > 2) {
            System.out.println("Этот леденец очень сладкий, так как содержит много сахара!");
        } else {
            System.out.println("Этот леденец имеет сбалансированный вкус.");
        }
    }
}
