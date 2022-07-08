package com.chutneytesting.infra;

public class WordFinder {

    public static String getWord(int lineIndex, int charIndex, String content) {
        String line = getLine(lineIndex, content);
        return getWord(charIndex, line);
    }

    static String getLine(int index, String content) {
        String[] split = content.split("\n");
        return split[index];
    }

    static String getWord(int charIndex, String line) {
        int lastSpace = line.substring(0, charIndex).lastIndexOf(" ");
        return line.substring(lastSpace+1, charIndex);
    }
}
