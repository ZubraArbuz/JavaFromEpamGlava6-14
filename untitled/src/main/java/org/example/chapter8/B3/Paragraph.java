package org.example.chapter8.B3;
import java.util.ArrayList;
import java.util.List;

public class Paragraph {
    private final List<Sentence> sentences = new ArrayList<>();

    public Paragraph(String paragraph) {
        String[] sentenceArray = paragraph.split("(?<=[.!?])\\s+");
        for (String sentence : sentenceArray) {
            sentences.add(new Sentence(sentence));
        }
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    @Override
    public String toString() {
        return String.join(" ", sentences.stream().map(Sentence::toString).toList());
    }
}

