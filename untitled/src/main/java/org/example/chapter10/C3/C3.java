package org.example.chapter10.C3;
import java.io.*;

public class C3 {
    public static void main(String[] args) {
        File sourceFile = new File("D:/3/Java/Glava6/untitled/src/main/java/org/example/chapter10/C3/sourceCode.java");
        File directory = new File("D:/3/Java/Glava6/untitled/src/test/java/org/example/chapter10/C3/outputDirectory");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File reversedFile = new File(directory, "reversedCode.java");
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(reversedFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String reversedLine = new StringBuilder(line).reverse().toString();
                writer.write(reversedLine);
                writer.newLine();
            }
            System.out.println("Текст успешно записан в файл с перевернутыми строками.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
