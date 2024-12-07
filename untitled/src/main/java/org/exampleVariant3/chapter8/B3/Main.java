package org.exampleVariant3.chapter8.B3;

public class Main {
    public static void main(String[] args) {
        String text = "Java is good language. Java is nice. Java is good.";

        String uniqueWord = TextProcessor.findUniqueWord(text);
        System.out.println("Уникальное слово является: " + uniqueWord);
    }
}

