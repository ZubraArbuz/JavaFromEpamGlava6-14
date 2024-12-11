package org.exampleVariant3.chapter14;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class Server {
    private static final int PORT = 3000;

    public static void main(String[] args) {
        String filePath = args.length > 0 ? args[0] : "untitled/src/main/java/org/exampleVariant3/chapter14/Stihi.txt";
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен. Ожидание подключения...");
            List<String> poems = loadPoems(filePath);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                    System.out.println("Клиент подключен: " + clientSocket.getInetAddress());
                    String randomPoem = getRandomPoem(poems);
                    out.println(randomPoem.replace("\n", System.lineSeparator()));
                    out.println("END");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> loadPoems(String filePath) throws IOException {
        String content = Files.readString(Paths.get(filePath));
        return Arrays.asList(content.split("(?m)^\\s*$"));
    }

    public static String getRandomPoem(List<String> poems) {
        Random random = new Random();
        return poems.get(random.nextInt(poems.size())).trim();
    }
}
