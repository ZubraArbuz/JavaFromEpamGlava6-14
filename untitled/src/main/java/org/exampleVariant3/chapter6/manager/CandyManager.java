package org.exampleVariant3.chapter6.manager;

public interface CandyManager {
    void produceCandyBatch(String type, int quantity);
    void getProductInfoByManufacturer(String manufacturer);
    void addOrUpdateCandy(String type, String manufacturer, double price, String[] ingredients);
    void addNewIngredient(String type,String ingredient);
    void createNewCandyType(String type, String manufacturer, String[] ingredients, double price);
    void discontinueCandyType(String type);
}
