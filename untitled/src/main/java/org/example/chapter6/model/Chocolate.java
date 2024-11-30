package org.example.chapter6.model;

import java.util.List;

public class Chocolate extends Candy {
    public Chocolate(String name, String manufacturer, double price, List<String> ingredients) {
        super(name, manufacturer, price, ingredients);
    }

    @Override
    public void specialMethod() {
        System.out.println("Шоколад состоит из 45% какао");
    }
}

