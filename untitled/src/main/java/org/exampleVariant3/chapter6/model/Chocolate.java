package org.exampleVariant3.chapter6.model;

import java.util.List;

public class Chocolate extends Candy {
    public Chocolate(String name, String manufacturer, double price, List<String> ingredients) {
        super(name, manufacturer, price, ingredients);
    }

    @Override
    public void specialMethod() {
        //в зависимости от цены можно определить какой процент какао содержит шоколад
        double cocoaPercentage = 0.0;
        if (getPrice() > 50) {
            cocoaPercentage = 70.0;
        } else if (getPrice() > 30) {
            cocoaPercentage = 50.0;
        } else {
            cocoaPercentage = 30.0;
        }
        System.out.println("Этот шоколад содержит " + cocoaPercentage + "% какао.");
    }

}

