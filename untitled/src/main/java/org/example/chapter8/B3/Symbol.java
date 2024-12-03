package org.example.chapter8.B3;

public class Symbol {
    private final char value;

    public Symbol(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

