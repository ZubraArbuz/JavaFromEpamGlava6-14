package org.example.chapter8.A3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class A3Test {

    @Test
    void testCorrectText() {
        String input = "радуга";
        String expected = "родуга";
        String actual = A3.correctText(input);
        assertEquals(expected, actual, "Текст должен быть исправлен корректно.");
    }

    @Test
    void testCorrectTextWithNoChanges() {
        String input = "пара";
        String expected = "пара";
        String actual = A3.correctText(input);
        assertEquals(expected, actual, "Текст без изменений должен остаться неизменным.");
    }
}
