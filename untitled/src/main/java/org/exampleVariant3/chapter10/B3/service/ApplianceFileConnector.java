package org.exampleVariant3.chapter10.B3.service;

import org.exampleVariant3.chapter10.B3.appliances.ElectricalAppliance;

import java.io.*;
import java.util.List;

public class ApplianceFileConnector {

    public void saveToFile(String fileName, List<ElectricalAppliance> appliances) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(appliances);
            System.out.println("Сохранено в файл: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ElectricalAppliance> loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<ElectricalAppliance>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
