package org.example.chapter6.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Candy {
    private String name;
    private String manufacturer;
    private double price;
    private List<String> ingredients;

    public Candy(String name, String manufacturer, double price, List<String> ingredients) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.ingredients = new ArrayList<>(ingredients);
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    public void removeIngredient(String ingredient) {
        ingredients.remove(ingredient);
    }

    public abstract void specialMethod();

    @Override
    public String toString() {
        return String.format("Название: %s, Производитель: %s, Цена: %.2f, Ингридиенты: %s",
                name, manufacturer, price, ingredients);
    }
}
