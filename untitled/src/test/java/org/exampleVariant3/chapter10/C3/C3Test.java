package org.exampleVariant3.chapter10.C3;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

class C3Test {

    private static final String TEST_DIRECTORY = "test-output";
    private static final String SOURCE_FILE = TEST_DIRECTORY + "/sourceCode.java";
    private static final String OUTPUT_DIRECTORY = TEST_DIRECTORY + "/outputDirectory";
    private static final String REVERSED_FILE = OUTPUT_DIRECTORY + "/reversedCode.java";

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(Paths.get(TEST_DIRECTORY));
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SOURCE_FILE))) {
            writer.write("Hello World!");
            writer.newLine();
            writer.write("Java Programming");
        }
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.walk(Paths.get(TEST_DIRECTORY))
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @Test
    void testCreateDirectory() {
        File directory = new File(OUTPUT_DIRECTORY);
        assertFalse(directory.exists());
        C3.createDirectory(directory);
        assertTrue(directory.exists());
        assertTrue(directory.isDirectory());
    }

    @Test
    void testReverseString() {
        String input = "Hello World!";
        String expected = "!dlroW olleH";
        String actual = C3.reverseString(input);
        assertEquals(expected, actual);
    }

    @Test
    void testReverseFileLines() throws IOException {
        File sourceFile = new File(SOURCE_FILE);
        File outputDirectory = new File(OUTPUT_DIRECTORY);
        C3.createDirectory(outputDirectory);
        File reversedFile = new File(REVERSED_FILE);

        C3.reverseFileLines(sourceFile, reversedFile);

        assertTrue(reversedFile.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(reversedFile))) {
            assertEquals("!dlroW olleH", reader.readLine());
            assertEquals("gnimmargorP avaJ", reader.readLine());
            assertNull(reader.readLine()); // Убедиться, что файл завершился
        }
    }
}
