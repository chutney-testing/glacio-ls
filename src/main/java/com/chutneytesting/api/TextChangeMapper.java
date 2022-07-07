package com.chutneytesting.api;

import org.eclipse.lsp4j.DidChangeTextDocumentParams;

import java.util.HashMap;
import java.util.Map;

public class TextChangeMapper {

    String fileUri;
    String textChange;
    Map<String, String> map = new HashMap<>();

    public TextChangeMapper(DidChangeTextDocumentParams didChangeTextDocumentParams) {
        this.fileUri = didChangeTextDocumentParams.getTextDocument().getUri();
        this.textChange = didChangeTextDocumentParams.getContentChanges().get(0).getText();
    }

    protected Map<String, String> toMap() {
       map.put(fileUri, textChange);
       return map;
    }
}
