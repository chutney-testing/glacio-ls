package com.chutneytesting.api;

import java.util.HashMap;
import java.util.Map;

public class ProjectDocuments {

    private final Map<String, String> documents = new HashMap<>();

    public ProjectDocuments() {}

    public void add(String uri, String content) {
        documents.put(uri, content);
    }

    public String get(String uri) {
        return documents.get(uri);
    }

}
