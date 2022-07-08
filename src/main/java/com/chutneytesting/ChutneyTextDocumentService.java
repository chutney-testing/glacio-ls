package com.chutneytesting;

import com.chutneytesting.api.ProjectDocuments;
import com.chutneytesting.api.SuggestionMapper;
import com.chutneytesting.infra.FileCompletionSuggestion;
import com.chutneytesting.infra.Suggestion;
import com.chutneytesting.infra.WordFinder;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.CompletionList;
import org.eclipse.lsp4j.CompletionParams;
import org.eclipse.lsp4j.DidChangeTextDocumentParams;
import org.eclipse.lsp4j.DidCloseTextDocumentParams;
import org.eclipse.lsp4j.DidOpenTextDocumentParams;
import org.eclipse.lsp4j.DidSaveTextDocumentParams;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.services.TextDocumentService;

public class ChutneyTextDocumentService implements TextDocumentService {

    private final ChutneyLanguageServer languageServer;
    private final LSClientLogger clientLogger;
    private final ProjectDocuments documents;

    public ChutneyTextDocumentService(ChutneyLanguageServer languageServer, ProjectDocuments documents) {
        this.languageServer = languageServer;
        this.clientLogger = LSClientLogger.getInstance();
        this.documents = documents;
    }

    @Override
    public void didOpen(DidOpenTextDocumentParams didOpenTextDocumentParams) {
        this.clientLogger.logMessage("Operation '" + "text/didOpen" +
                "' {fileUri: '" + didOpenTextDocumentParams.getTextDocument().getUri() + "'} opened");
    }

    @Override
    public void didChange(DidChangeTextDocumentParams didChangeTextDocumentParams) {
        this.documents.add(
                didChangeTextDocumentParams.getTextDocument().getUri(),
                didChangeTextDocumentParams.getContentChanges().get(0).getText()
        );
    }

    @Override
    public void didClose(DidCloseTextDocumentParams didCloseTextDocumentParams) {
        this.clientLogger.logMessage("Operation '" + "text/didClose" +
                "' {fileUri: '" + didCloseTextDocumentParams.getTextDocument().getUri() + "'} Closed");
    }

    @Override
    public void didSave(DidSaveTextDocumentParams didSaveTextDocumentParams) {
        this.clientLogger.logMessage("Operation '" + "text/didSave" +
                "' {fileUri: '" + didSaveTextDocumentParams.getTextDocument().getUri() + "'} Saved");
    }

    @Override
    public CompletableFuture<Either<List<CompletionItem>, CompletionList>> completion(CompletionParams params) {
        return CompletableFuture.supplyAsync(() -> {
            this.clientLogger.logMessage("Operation '" + "text/completion");
            clientLogger.logMessage("POSITION DocumentService uri : " + params.getTextDocument().getUri());

            String documentContent = this.documents.get(params.getTextDocument().getUri());
            String character = WordFinder.getWord(params.getPosition().getLine(), params.getPosition().getCharacter(), documentContent);
            FileCompletionSuggestion fileCompletionSuggestion = new FileCompletionSuggestion("completion.txt");
            List<Suggestion> suggestions = fileCompletionSuggestion.getSuggestion(character);

            List<CompletionItem> completionItems = SuggestionMapper.toCompletionItem(suggestions);

            return Either.forLeft(completionItems);
        });
    }

}
