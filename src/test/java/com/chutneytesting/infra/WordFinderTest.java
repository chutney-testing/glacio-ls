package com.chutneytesting.infra;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class WordFinderTest {

/*    @Test
    public void should_return_first_word_in_userEntry() {
        URL resource = this.getClass().getClassLoader().getResource("sample.txt");
        TextDocumentIdentifier textDocument = new TextDocumentIdentifier(resource.getPath());
        Position textPosition = new Position(0, 3);
        CompletionParams position = new CompletionParams(textDocument, textPosition);

        String word = UserEntries.getWord(lineIndex, charIndex, content);

        assertThat(word).isEqualTo("TOT");
    }*/

    @Test
    public void should_return_first_word_in_userEntry() {
        int lineIndex = 1;
        int charIndex = 9;
        String content = "Le lorem ipsum est, en imprimerie,\n" +
                "une suite de mots sans signification utilisée à titre provisoire\n" +
                "pour calibrer une mise en page, le texte définitif venant remplacer";

        String word = WordFinder.getWord(lineIndex, charIndex, content);

        assertThat(word).isEqualTo("suite");
    }

    @Test
    public void should_return_a_given_line() {
        int index = 1;
        String content = "Le lorem ipsum est, en imprimerie,\n" +
                "une suite de mots sans signification utilisée à titre provisoire\n" +
                "pour calibrer une mise en page, le texte définitif venant remplacer";

        String line = WordFinder.getLine(index, content);

        assertThat(line).isEqualTo("une suite de mots sans signification utilisée à titre provisoire");
    }

    @Test
    void getWord() {
        int charIndex = 9;
        String content = "une suite de mots sans signification utilisée à titre provisoire";

        String word = WordFinder.getWord(charIndex, content);

        assertThat(word).isEqualTo("suite");
    }
}