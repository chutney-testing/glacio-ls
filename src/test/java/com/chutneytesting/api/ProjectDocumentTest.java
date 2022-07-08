package com.chutneytesting.api;

import org.assertj.core.api.Assertions;
import org.eclipse.lsp4j.DidChangeTextDocumentParams;
import org.eclipse.lsp4j.TextDocumentContentChangeEvent;
import org.eclipse.lsp4j.VersionedTextDocumentIdentifier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ProjectDocumentTest {

    @Test
    public void should_put_text_change_in_a_map() {

        VersionedTextDocumentIdentifier textDocument = new VersionedTextDocumentIdentifier("/Users/adriengogois/Desktop/lsp-chutney/chutney_plugin/index.chutney", 2);
        TextDocumentContentChangeEvent userChange = new TextDocumentContentChangeEvent("Test");
        List<TextDocumentContentChangeEvent> contentChange = new ArrayList<>();
        contentChange.add(userChange);
        DidChangeTextDocumentParams didChangeTextDocumentParams = new DidChangeTextDocumentParams(textDocument, contentChange);

        ProjectDocument projectDocument = new ProjectDocument(didChangeTextDocumentParams);
        Assertions.assertThat(projectDocument.toMap()).containsEntry("/Users/adriengogois/Desktop/lsp-chutney/chutney_plugin/index.chutney", "Test");
    }

}