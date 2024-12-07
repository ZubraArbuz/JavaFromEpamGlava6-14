package org.exampleVariant3.chapter10.B3;

import org.exampleVariant3.chapter10.B3.appliances.Fridge;
import org.exampleVariant3.chapter10.B3.appliances.TV;
import org.exampleVariant3.chapter10.B3.service.ApplianceFileConnector;
import org.exampleVariant3.chapter10.B3.service.ApplianceService;

public class Main {
    public static void main(String[] args) {
        ApplianceService service = new ApplianceService();
        ApplianceFileConnector fileConnector = new ApplianceFileConnector();
        String fileName = "C:/sd/appliances.dat";

        service.addAppliance(new Fridge("Холодильник", 200));
        service.addAppliance(new TV("Телевизор", 150));
        service.getAllAppliances().get(0).plugIn();

        fileConnector.saveToFile(fileName, service.getAllAppliances());

        var loadedAppliances = fileConnector.loadFromFile(fileName);
        System.out.println("Загруженные приборы:");
        if (loadedAppliances != null) {
            loadedAppliances.forEach(System.out::println);
        }
    }
}