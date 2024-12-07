package org.exampleVariant3.chapter6.model;

import java.util.List;

public class Lollipop extends Candy {
    public Lollipop(String name, String manufacturer, double price, List<String> ingredients) {
        super(name, manufacturer, price, ingredients);
    }

    @Override
    public void specialMethod() {
        System.out.println("Имеет форму петуха");
    }
}
