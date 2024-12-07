package org.exampleVariant3.chapter6;

import org.exampleVariant3.chapter6.manager.CandyManager;
import org.exampleVariant3.chapter6.manager.CandyManagerImpl;
import org.exampleVariant3.chapter6.model.Candy;
import org.exampleVariant3.chapter6.model.Chocolate;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CandyManager manager = new CandyManagerImpl();
        Candy chocolate = new Chocolate("Темный шоколад","Россия",40.0, List.of("Какао","Молоко","Сахар"));
        System.out.println();
        manager.addNewIngredient("Сахар");
        manager.addNewIngredient("Какао");
        manager.addNewIngredient("Молоко");
        System.out.println();
        chocolate.specialMethod();
        System.out.println();
        manager.createNewCandyType("Шоколад", "Россия", new String[]{"Сахар", "Какао", "Молоко"}, 10.5);
        manager.createNewCandyType("Леденец", "Китай", new String[]{"Сахар", "Л"}, 5.0);
        System.out.println();
        manager.addOrUpdateCandy("Шоколад","ШарбунаевШоколад",33.2,new String[]{"Ванилин"});
        System.out.println();
        manager.getProductInfoByManufacturer("ШарбунаевШоколад");
        manager.getProductInfoByManufacturer("ЛеденецОАО");
        System.out.println();
        manager.produceCandyBatch("Шоколад", 100);
        manager.discontinueCandyType("Леденец");
    }

}
