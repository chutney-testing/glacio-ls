package com.chutneytesting.api;

import org.eclipse.lsp4j.DidChangeTextDocumentParams;

import java.util.HashMap;
import java.util.Map;

public class ProjectDocument {

    private final String documentUri;
    private final String documentTextChange;
    private final Map<String ,String> map = new HashMap<>();

    public ProjectDocument(DidChangeTextDocumentParams didChangeTextDocumentParams) {
        this.documentUri = didChangeTextDocumentParams.getTextDocument().getUri();
        this.documentTextChange = didChangeTextDocumentParams.getContentChanges().get(0).getText();
    }
    public Map<String, String> toMap() {
        map.put(documentUri, documentTextChange);
        return map;
    }
}
