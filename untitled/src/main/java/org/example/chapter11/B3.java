package org.example.chapter11;

import java.util.*;
import java.util.stream.Collectors;

public class B3 {
    static class Node implements Comparable<Node> {
        String word;
        int frequency;
        Node left, right;

        Node(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }

        Node(Node left, Node right) {
            this.word = null;
            this.frequency = left.frequency + right.frequency;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.frequency, other.frequency);
        }
    }

    public static Node buildHuffmanTree(Map<String, Integer> frequencyMap) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        frequencyMap.forEach((word, freq) -> priorityQueue.add(new Node(word, freq)));

        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            priorityQueue.add(new Node(left, right));
        }

        return priorityQueue.poll(); // Корень дерева
    }

    public static void generateCodes(Node node, String code, Map<String, String> huffmanCodes) {
        if (node == null) return;

        if (node.word != null) { // Лист
            huffmanCodes.put(node.word, code);
        } else {
            generateCodes(node.left, code + "0", huffmanCodes);
            generateCodes(node.right, code + "1", huffmanCodes);
        }
    }

    // Сжатие текста
    public static String compressText(String text, Map<String, String> huffmanCodes) {
        return Arrays.stream(text.split("\\s+"))
                .map(huffmanCodes::get)
                .collect(Collectors.joining(" "));
    }

    // Распаковка текста
    public static String decompressText(String compressedText, Node root) {
        StringBuilder decompressed = new StringBuilder();
        Node current = root;
        for (char bit : compressedText.replace(" ", "").toCharArray()) {
            current = (bit == '0') ? current.left : current.right;

            if (current.word != null) { // Достигли листа
                decompressed.append(current.word).append(" ");
                current = root;
            }
        }
        return decompressed.toString().trim();
    }

    public static void main(String[] args) {
        String text = "this is an example of a huffman tree huffman huffman";

        Map<String, Integer> frequencyMap = Arrays.stream(text.split("\\s+"))
                .collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));

        System.out.println("Частотный словарь: " + frequencyMap);

        Node root = buildHuffmanTree(frequencyMap);

        Map<String, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);

        System.out.println("Префиксные коды: " + huffmanCodes);

        String compressedText = compressText(text, huffmanCodes);
        System.out.println("Сжатый текст: " + compressedText);

        String decompressedText = decompressText(compressedText, root);
        System.out.println("Распакованный текст: " + decompressedText);

        System.out.println("Сжатие корректно: " + text.equals(decompressedText));
    }
}
