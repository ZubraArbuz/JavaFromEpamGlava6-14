package org.example.chapter10.C3;

import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class C3Test {

    private File sourceFile;
    private File directory;
    private File reversedFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Подготавливаем тестовые файлы
        sourceFile = new File("D:/3/Java/Glava6/untitled/src/test/java/org/example/chapter10/C3/sourceCode.java");
        directory = new File("D:/3/Java/Glava6/untitled/src/test/java/org/example/chapter10/C3/outputDirectory");

        // Создаем директорию и файл с исходными данными, если они еще не существуют
        if (!directory.exists()) {
            directory.mkdirs();
        }
        reversedFile = new File(directory, "reversedCode.java");

        if (!sourceFile.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(sourceFile))) {
                writer.write("package org.example.chapter10.C3;\n");
                writer.write("public class sourceCode {\n");
                writer.write("    public static void main(String[] args) {\n");
                writer.write("        System.out.println(\"Hello world!\");\n");
                writer.write("    }\n");
                writer.write("}\n");
            }
        }
    }

    @Test
    public void testDirectoryCreation() {
        // Проверяем, что директория была создана
        assertTrue(directory.exists(), "Output directory should exist");
    }

    @Test
    public void testFileReversal() throws IOException {
        // Запускаем программу
        System.out.println("Запуск программы...");
        C3.main(new String[] {});

        // Добавляем задержку, чтобы программа успела создать файл
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        // Проверяем, что файл был создан
        assertTrue(reversedFile.exists(), "Reversed code file should exist");

        // Проверяем содержимое файла
        try (BufferedReader reader = new BufferedReader(new FileReader(reversedFile))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                switch (lineNumber) {
                    case 1:
                        assertEquals(";3C.01retpahc.elpmaxe.gro egakcap", line, "Line 1 should be reversed");
                        break;
                    case 2:
                        assertEquals("{ edoCecruos ssalc cilbup", line, "Line 2 should be reversed");
                        break;
                    case 3:
                        assertEquals("{ )sgra ][gnirtS(niam diov citats cilbup    ", line, "Line 3 should be reversed");
                        break;
                    case 4:
                        assertEquals(";)\"!dlrow olleH\"(nltnirp.tuo.metsyS        ", line, "Line 3 should be reversed");
                    default:
                        break;
                }
            }
        }
    }

    @Test
    public void testIOExceptionHandling() {
        // Стилизуем ошибку с несуществующим файлом
        File invalidFile = new File("invalidFile.java");

        assertDoesNotThrow(() -> C3.main(new String[] {}), "IOException should not throw");
    }

}
