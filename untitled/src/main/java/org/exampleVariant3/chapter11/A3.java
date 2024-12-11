package org.exampleVariant3.chapter11;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class A3 {

    public List<Path> getAllPaths(String directoryPath) throws IOException {
        return Files.walk(Paths.get(directoryPath))
                .collect(Collectors.toList());
    }

    public List<Path> getFilesOnly(List<Path> allPaths) {
        return allPaths.stream()
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String directoryPath = "untitled/src/main/java/org/exampleVariant3";
        A3 a3 = new A3();
        try {
            List<Path> allPaths = a3.getAllPaths(directoryPath);
            List<Path> filesOnly = a3.getFilesOnly(allPaths);

            System.out.println("Все элементы каталога:");
            allPaths.forEach(System.out::println);

            System.out.println("\nТолько файлы:");
            filesOnly.forEach(System.out::println);

        } catch (IOException e) {
            System.err.println("Ошибка чтения каталога: " + e.getMessage());
        }
    }
}
