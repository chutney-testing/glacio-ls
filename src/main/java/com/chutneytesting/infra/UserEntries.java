package com.chutneytesting.infra;

import com.chutneytesting.LSClientLogger;
import org.eclipse.lsp4j.CompletionParams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserEntries {

    private static LSClientLogger clientLogger;
    
    public static String getWord(CompletionParams position) {
        String line = getLine(position);
        int inlinePosition = position.getPosition().getCharacter();
        return getWord(line, inlinePosition);
    }

    protected static String getLine(CompletionParams position) {
        try {
            return Files.readAllLines(Paths.get(position.getTextDocument().getUri())).get(position.getPosition().getLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static String getWord(String line, int position) {
        return line.substring(0, position);
    }
}
