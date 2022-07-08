package com.chutneytesting;

import com.chutneytesting.api.TextChangeMapper;
import com.chutneytesting.infra.FileCompletionSuggestion;
import com.chutneytesting.infra.Suggestion;
import com.chutneytesting.api.SuggestionMapper;
import com.chutneytesting.infra.UserEntries;
import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.CompletionList;
import org.eclipse.lsp4j.CompletionParams;
import org.eclipse.lsp4j.DidChangeTextDocumentParams;
import org.eclipse.lsp4j.DidCloseTextDocumentParams;
import org.eclipse.lsp4j.DidOpenTextDocumentParams;
import org.eclipse.lsp4j.DidSaveTextDocumentParams;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.services.TextDocumentService;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ChutneyTextDocumentService implements TextDocumentService {

    private ChutneyLanguageServer languageServer;
    private LSClientLogger clientLogger;

    public ChutneyTextDocumentService(ChutneyLanguageServer languageServer) {
        this.languageServer = languageServer;
        this.clientLogger = LSClientLogger.getInstance();
    }

    @Override
    public void didOpen(DidOpenTextDocumentParams didOpenTextDocumentParams) {
        this.clientLogger.logMessage("Operation '" + "text/didOpen" +
                "' {fileUri: '" + didOpenTextDocumentParams.getTextDocument().getUri() + "'} opened");
    }

    @Override
    public void didChange(DidChangeTextDocumentParams didChangeTextDocumentParams) {
        Map<String, String> map = TextChangeMapper.toMap(didChangeTextDocumentParams);
        map.forEach((key, value) -> this.clientLogger.logMessage("TEST MAP -> " + key + " : " + value));
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
    public CompletableFuture<Either<List<CompletionItem>, CompletionList>> completion(CompletionParams position) {
        return CompletableFuture.supplyAsync(() -> {
            this.clientLogger.logMessage("Operation '" + "text/completion");
            clientLogger.logMessage("POSITION DocumentService uri : " + position.getTextDocument().getUri());

            String character = UserEntries.getWord(position);
            FileCompletionSuggestion fileCompletionSuggestion = new FileCompletionSuggestion("completion.txt");
            List<Suggestion> suggestions = fileCompletionSuggestion.getSuggestion(character);

            List<CompletionItem> completionItems = SuggestionMapper.toCompletionItem(suggestions);


            /*CompletionItem completionItem = new CompletionItem();
            completionItem.setInsertText("sayHello() {\n    print(\"hello\")\n}");
            completionItem.setLabel("sayHello()");
            completionItem.setKind(CompletionItemKind.Snippet);
            completionItem.setDetail("sayHello()\n this will say hello to the people");*/

            return Either.forLeft(completionItems);
        });
    }




}
