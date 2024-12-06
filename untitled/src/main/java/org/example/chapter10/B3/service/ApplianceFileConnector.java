package org.example.chapter10.B3.service;

import org.example.chapter10.B3.appliances.ElectricalAppliance;

import java.io.*;
import java.util.List;

public class ApplianceFileConnector {

    // Сохранение списка приборов в файл
    public void saveToFile(String fileName, List<ElectricalAppliance> appliances) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(appliances);
            System.out.println("Сохранено в файл: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Загрузка списка приборов из файла
    @SuppressWarnings("unchecked")
    public List<ElectricalAppliance> loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<ElectricalAppliance>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
