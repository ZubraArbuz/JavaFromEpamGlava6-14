package org.exampleVariant3.chapter8.C3;

public class C3 {
    public static String removeMaxSubstring(String input) {
        int maxLen = 0;
        int startIdx = 0;
        int endIdx = 0;

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            int lastIndex = input.lastIndexOf(currentChar);
            if (lastIndex > i && (lastIndex - i + 1) > maxLen) {
                maxLen = lastIndex - i + 1;
                startIdx = i;
                endIdx = lastIndex;
            }
        }

        if (maxLen > 0) {
            return input.substring(0, startIdx) + input.substring(endIdx + 1);
        }

        return input;
    }

    public static void main(String[] args) {
        String input = "diodparent";
        System.out.println("Исходная строка: " + input);
        String result = removeMaxSubstring(input);
        System.out.println("Результат: " + result);
    }
}
