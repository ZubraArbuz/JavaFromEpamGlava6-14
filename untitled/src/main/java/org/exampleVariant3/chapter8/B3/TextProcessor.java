package org.exampleVariant3.chapter8.B3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TextProcessor {
    public static String findUniqueWord(String text) {
        String[] paragraphs = text.split("\\n\\s*");
        Paragraph firstParagraph = new Paragraph(paragraphs[0]);
        List<Sentence> sentences = firstParagraph.getSentences();

        Sentence firstSentence = sentences.get(0);
        Set<String> firstSentenceWords = new HashSet<>();
        Set<String> otherSentenceWords = new HashSet<>();

        for (Word word : firstSentence.getWords()) {
            firstSentenceWords.add(word.getValue().toLowerCase());
        }

        for (int i = 1; i < sentences.size(); i++) {
            for (Word word : sentences.get(i).getWords()) {
                otherSentenceWords.add(word.getValue().toLowerCase());
            }
        }

        firstSentenceWords.removeAll(otherSentenceWords);

        return firstSentenceWords.isEmpty()
                ? "Нету уникальных слов"
                : firstSentenceWords.iterator().next();
    }
}
