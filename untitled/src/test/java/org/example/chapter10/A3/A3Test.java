package org.example.chapter10.A3;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class A3Test {

    @Test
    void testFileProcessing() throws IOException {
        Path inputFile = Files.createTempFile("input", ".txt");
        Path outputFile = Files.createTempFile("output", ".txt");
        Files.write(inputFile, Arrays.asList(
                "apple banana orange",
                "elephant umbrella",
                "cat dog"
        ));
        A3.main(new String[]{inputFile.toString(), outputFile.toString()});
        List<String> result = Files.readAllLines(outputFile);
        List<String> expected = Arrays.asList("apple", "orange", "elephant", "umbrella");
        assertEquals(expected, result, "Output file should contain only words starting with vowels");
        Files.deleteIfExists(inputFile);
        Files.deleteIfExists(outputFile);
    }

    @Test
    void testFindWordsStartingWithVowel_English() {
        String input = "apple banana orange elephant umbrella cat dog";
        List<String> expected = Arrays.asList("apple", "orange", "elephant", "umbrella");
        List<String> actual = A3.findWordsStartingWithVowel(input);
        assertEquals(expected, actual, "Should find words starting with vowels");
    }

    @Test
    void testFindWordsStartingWithVowel_Cyrillic() {
        String input = "апельсин банан огурец ёж утка ";
        List<String> expected = Arrays.asList("апельсин", "огурец", "ёж","утка");
        List<String> actual = A3.findWordsStartingWithVowel(input);
        assertEquals(expected, actual, "Should find words starting with Cyrillic vowels");
    }

    @Test
    void testFindWordsStartingWithVowel_EmptyLine() {
        String input = "";
        List<String> expected = Collections.emptyList();
        List<String> actual = A3.findWordsStartingWithVowel(input);
        assertEquals(expected, actual, "Empty input should return an empty list");
    }

    @Test
    void testFindWordsStartingWithVowel_NoVowels() {
        String input = "cat dog banana";
        List<String> expected = Collections.emptyList();
        List<String> actual = A3.findWordsStartingWithVowel(input);
        assertEquals(expected, actual, "Input with no vowel-starting words should return an empty list");
    }

    @Test
    void testFindWordsStartingWithVowel_MixedCase() {
        String input = "Apple orange Elephant Cat dog";
        List<String> expected = Arrays.asList("Apple", "orange", "Elephant");
        List<String> actual = A3.findWordsStartingWithVowel(input);
        assertEquals(expected, actual, "Should correctly handle mixed-case words");
    }
}
