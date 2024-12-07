package org.exampleVariant3.chapter8.B3;
import java.util.ArrayList;
import java.util.List;

public class Sentence {
    private final List<Word> words = new ArrayList<>();

    public Sentence(String sentence) {
        String[] wordArray = sentence.split("\\W+");
        for (String word : wordArray) {
            if (!word.isEmpty()) {
                words.add(new Word(word));
            }
        }
    }

    public List<Word> getWords() {
        return words;
    }

    @Override
    public String toString() {
        return String.join(" ", words.stream().map(Word::toString).toList());
    }
}

