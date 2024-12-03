package org.example.chapter8.A3;

import java.util.Scanner;

public class A3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите текст:");
        String inputText = scanner.nextLine();

        String correctedText = correctText(inputText);

        System.out.println("Исправленный текст:");
        System.out.println(correctedText);
    }

    public static String correctText(String text) {
        return text.replaceAll("(?i)(?<=р)а(?=\\p{L})", "о");
    }
}
