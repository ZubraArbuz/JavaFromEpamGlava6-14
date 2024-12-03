package org.example.chapter8.B3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextProcessorTest {

    @Test
    void testFindUniqueWord_withUniqueWordInFirstSentence() {
        String text = "Java is good language. Java is nice. Java is good.";
        String result = TextProcessor.findUniqueWord(text);
        assertEquals("language", result, "Уникальное слово должно быть 'language'");
    }

    @Test
    void testFindUniqueWord_noUniqueWord() {
        String text = "Java is good. Java is nice. Java is good.";
        String result = TextProcessor.findUniqueWord(text);
        assertEquals("Нету уникальных слов", result, "Если уникального слова нет, должно быть возвращено 'Нету уникальных слов'");
    }

    @Test
    void testFindUniqueWord_withMultipleParagraphs() {
        String text = "Java is a good language. Java is a programming language.";
        String result = TextProcessor.findUniqueWord(text);
        assertEquals("good", result, "Уникальное слово должно быть 'good'");
    }

    @Test
    void testFindUniqueWord_withEmptyText() {
        String text = "";
        String result = TextProcessor.findUniqueWord(text);
        assertEquals("Нету уникальных слов", result, "Для пустого текста результат должен быть 'Нету уникальных слов'");
    }
}
