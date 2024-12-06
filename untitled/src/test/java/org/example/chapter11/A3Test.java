package org.example.chapter11;

import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class A3Test {

    private A3 a3;
    private Path testDirectory;
    private Path testFile1;
    private Path testFile2;
    private Path testSubDirectory;

    @BeforeEach
    void setUp() throws IOException {
        a3 = new A3();
        testDirectory = Files.createTempDirectory("testDirectory");
        testFile1 = Files.createFile(testDirectory.resolve("testFile1.txt"));
        testFile2 = Files.createFile(testDirectory.resolve("testFile2.txt"));
        testSubDirectory = Files.createDirectory(testDirectory.resolve("subDirectory"));
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(testFile1);
        Files.deleteIfExists(testFile2);
        Files.deleteIfExists(testSubDirectory);
        Files.deleteIfExists(testDirectory);
    }

    @Test
    void testGetAllPaths() throws IOException {
        List<Path> allPaths = a3.getAllPaths(testDirectory.toString());
        assertTrue(allPaths.contains(testFile1));
        assertTrue(allPaths.contains(testFile2));
        assertTrue(allPaths.contains(testSubDirectory));
        assertTrue(allPaths.contains(testDirectory));
    }

    @Test
    void testGetFilesOnly() throws IOException {
        List<Path> allPaths = a3.getAllPaths(testDirectory.toString());
        List<Path> filesOnly = a3.getFilesOnly(allPaths);

        assertTrue(filesOnly.contains(testFile1));
        assertTrue(filesOnly.contains(testFile2));
        assertFalse(filesOnly.contains(testSubDirectory));
        assertFalse(filesOnly.contains(testDirectory));
    }

    @Test
    void testDirectoryDoesNotExist() {
        String noExistPath = "no/exist/path";
        assertThrows(IOException.class, () -> a3.getAllPaths(noExistPath));
    }
}
