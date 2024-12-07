package org.example.chapter10.B3;

import org.example.chapter10.B3.appliances.ElectricalAppliance;
import org.example.chapter10.B3.appliances.Fridge;
import org.example.chapter10.B3.appliances.TV;
import org.example.chapter10.B3.service.ApplianceFileConnector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplianceFileConnectorTest {

    private ApplianceFileConnector fileConnector;
    private List<ElectricalAppliance> appliances;
    private final String fileName = "test_appliances.dat";

    @BeforeEach
    void setUp() {
        fileConnector = new ApplianceFileConnector();
        appliances = new ArrayList<>();
        appliances.add(new Fridge("Test Fridge", 200));
        appliances.add(new TV("Test TV", 150));
    }

    @AfterEach
    void tearDown() {
        // Удаление тестового файла после завершения тестов
        File file = new File(fileName);
        if (file.exists()) {
            assertTrue(file.delete(), "Не удалось удалить тестовый файл");
        }
    }

    @Test
    void testSaveAndLoad() {
        fileConnector.saveToFile(fileName, appliances);

        File file = new File(fileName);
        assertTrue(file.exists(), "Файл для сериализации не был создан");

        List<ElectricalAppliance> loadedAppliances = fileConnector.loadFromFile(fileName);

        assertNotNull(loadedAppliances, "Загруженные данные не должны быть null");
        assertEquals(appliances.size(), loadedAppliances.size(), "Размер списка загруженных объектов не совпадает");

        for (int i = 0; i < appliances.size(); i++) {
            ElectricalAppliance original = appliances.get(i);
            ElectricalAppliance loaded = loadedAppliances.get(i);

            assertEquals(original.getName(), loaded.getName(), "Названия объектов не совпадают");
            assertEquals(original.getPower(), loaded.getPower(), "Мощность объектов не совпадает");
            assertEquals(original.isOn(), loaded.isOn(), "Состояние объектов (включён/выключен) не совпадает");
        }
    }

    @Test
    void testSaveWithEmptyList() {
        List<ElectricalAppliance> emptyList = new ArrayList<>();
        fileConnector.saveToFile(fileName, emptyList);

        File file = new File(fileName);
        assertTrue(file.exists(), "Файл для сериализации пустого списка не был создан");

        List<ElectricalAppliance> loadedAppliances = fileConnector.loadFromFile(fileName);

        assertNotNull(loadedAppliances, "Загруженные данные не должны быть null");
        assertTrue(loadedAppliances.isEmpty(), "Список загруженных объектов должен быть пустым");
    }
}
