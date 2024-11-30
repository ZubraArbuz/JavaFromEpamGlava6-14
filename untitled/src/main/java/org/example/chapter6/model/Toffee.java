package org.example.chapter6.model;

import java.util.List;

public class Toffee extends Candy {
    public Toffee(String name, String manufacturer, double price, List<String> ingredients) {
        super(name, manufacturer, price, ingredients);
    }

    @Override
    public void specialMethod() {
        System.out.println("Ирис очень тягучий");
    }
}
