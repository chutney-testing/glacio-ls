package com.chutneytesting.api;

import org.eclipse.lsp4j.DidChangeTextDocumentParams;

import java.util.HashMap;
import java.util.Map;

public class TextChangeMapper {
    public static Map<String, String> toMap(DidChangeTextDocumentParams didChangeTextDocumentParams) {
        Map<String, String> map = new HashMap<>();
       map.put(didChangeTextDocumentParams.getTextDocument().getUri(), didChangeTextDocumentParams.getContentChanges().get(0).getText());
       return map;
    }
}
