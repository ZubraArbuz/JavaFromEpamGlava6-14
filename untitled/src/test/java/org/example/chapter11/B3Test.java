package org.example.chapter11;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class B3Test {

    @Test
    void testBuildHuffmanTree() {
        Map<String, Integer> frequencyMap = Map.of(
                "a", 5,
                "b", 9,
                "c", 12,
                "d", 13,
                "e", 16,
                "f", 45
        );

        B3.Node root = B3.buildHuffmanTree(frequencyMap);
        assertNotNull(root);
        assertEquals(100, root.frequency, "Сумма частот должна равняться общей частоте");
    }

    @Test
    void testGenerateCodes() {
        Map<String, Integer> frequencyMap = Map.of(
                "a", 5,
                "b", 9,
                "c", 12,
                "d", 13,
                "e", 16,
                "f", 45
        );

        B3.Node root = B3.buildHuffmanTree(frequencyMap);
        Map<String, String> huffmanCodes = new HashMap<>();
        B3.generateCodes(root, "", huffmanCodes);

        assertEquals(6, huffmanCodes.size(), "Должно быть сгенерировано 6 кодов");
        assertTrue(huffmanCodes.values().stream().allMatch(code -> code.matches("[01]+")),
                "Все коды должны состоять из 0 и 1");
    }

    @Test
    void testCompressAndDecompress() {
        String text = "this is an example of a huffman tree huffman huffman";
        Map<String, Integer> frequencyMap = Map.of(
                "this", 1,
                "is", 1,
                "an", 1,
                "example", 1,
                "of", 1,
                "a", 1,
                "huffman", 3,
                "tree", 1
        );

        B3.Node root = B3.buildHuffmanTree(frequencyMap);
        Map<String, String> huffmanCodes = new HashMap<>();
        B3.generateCodes(root, "", huffmanCodes);

        String compressedText = B3.compressText(text, huffmanCodes);
        assertNotNull(compressedText, "Сжатый текст не должен быть null");
        assertFalse(compressedText.isEmpty(), "Сжатый текст не должен быть пустым");

        String decompressedText = B3.decompressText(compressedText, root);
        assertEquals(text, decompressedText, "Распакованный текст должен совпадать с исходным");
    }
}
