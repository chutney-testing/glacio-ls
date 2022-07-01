package com.chutneytesting.infra;


import org.assertj.core.api.Assertions;
import org.eclipse.lsp4j.CompletionParams;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.TextDocumentIdentifier;
import org.junit.jupiter.api.Test;

class UserEntriesTest {
    @Test
    public void should_return_first_word_in_userEntry() {
        TextDocumentIdentifier textDocument = new TextDocumentIdentifier("/Users/adriengogois/Desktop/lsp-chutney/chutney-language-server/src/test/resources/sample.txt");
        Position textPosition = new Position(0, 3);
        CompletionParams position = new CompletionParams(textDocument, textPosition);

        String word = UserEntries.getWord(position);

        Assertions.assertThat(word).isEqualTo("TOT");
    }
}