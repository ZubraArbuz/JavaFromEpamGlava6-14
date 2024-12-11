package org.exampleVariant3.chapter14;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.Socket;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {

    @Test
    void testLoadPoems() throws IOException {
        String testContent = """
                Стихотворение 1
                Первая строка
                
                Стихотворение 2
                Вторая строка
                
                Стихотворение 3
                Третья строка
                """;

        Path tempFile = Files.createTempFile("testPoems", ".txt");
        Files.writeString(tempFile, testContent);

        List<String> poems = Server.loadPoems(tempFile.toString());

        assertEquals(3, poems.size());
        assertTrue(poems.get(0).contains("Стихотворение 1"));
        assertTrue(poems.get(1).contains("Стихотворение 2"));
        assertTrue(poems.get(2).contains("Стихотворение 3"));

        Files.delete(tempFile);
    }

    @Test
    void testGetRandomPoem() {
        List<String> poems = List.of("Стих 1", "Стих 2", "Стих 3");
        String randomPoem = Server.getRandomPoem(poems);
        assertTrue(poems.contains(randomPoem));
    }

    @Test
    void testServerClientInteraction() throws IOException {
        String testContent = """
            Стихотворение 1
            Первая строка
            
            Стихотворение 2
            Вторая строка
            """;

        Path tempFile = Files.createTempFile("testPoems", ".txt");
        Files.writeString(tempFile, testContent);

        Thread serverThread = new Thread(() -> {
            try {
                Server.main(new String[]{tempFile.toString()});
            } catch (Exception e) {
                fail("Сервер не запустился: " + e.getMessage());
            }
        });
        serverThread.start();

        try (Socket socket = new Socket("localhost", 3000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String line;
            StringBuilder poem = new StringBuilder();

            while ((line = in.readLine()) != null) {
                if ("END".equals(line)) break;
                poem.append(line).append("\n");
            }

            String result = poem.toString().trim();
            assertTrue(result.contains("Стихотворение 1") || result.contains("Стихотворение 2"));
        }

        serverThread.interrupt();
        Files.delete(tempFile);
    }


}
