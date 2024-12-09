package org.exampleVariant3.chapter10.C3;

import java.io.*;

public class C3 {
    public static void main(String[] args) {
        File sourceFile = new File("untitled/src/main/java/org/exampleVariant3/chapter10/C3/sourceCode.java");
        File directory = new File("untitled/src/main/java/org/exampleVariant3/chapter10/C3/outputDirectory");

        createDirectory(directory);
        File reversedFile = new File(directory, "reversedCode.java");

        reverseFileLines(sourceFile, reversedFile);
    }

    public static void createDirectory(File directory) {
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static void reverseFileLines(File sourceFile, File targetFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String reversedLine = reverseString(line);
                writer.write(reversedLine);
                writer.newLine();
            }
            System.out.println("Текст успешно записан в файл с перевернутыми строками.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String reverseString(String line) {
        return new StringBuilder(line).reverse().toString();
    }
}
