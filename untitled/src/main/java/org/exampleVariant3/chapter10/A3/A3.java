package org.exampleVariant3.chapter10.A3;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class A3 {
    public static List<String> findWordsStartingWithVowel(String line) {
        return Arrays.stream(line.split("\\s+"))
                .filter(word -> word.matches("^[AEIOUaeiouАЕЁИОУЫЭЮЯаеёиоуыэюя].*"))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java VowelWordsFinder <inputFile> <outputFile>");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        try {
            List<String> lines = Files.readAllLines(Paths.get(inputFilePath));
            List<String> vowelWords = new ArrayList<>();
            for (String line : lines) {
                List<String> words = Arrays.stream(line.split("\\s+"))
                        .filter(word -> word.matches("^[AEIOUaeiouАЕЁИОУЫЭЮЯаеёиоуыэюя].*"))
                        .collect(Collectors.toList());
                vowelWords.addAll(words);
            }
            Files.write(Paths.get(outputFilePath), vowelWords);

            System.out.println("Words starting with a vowel have been written to: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
}
