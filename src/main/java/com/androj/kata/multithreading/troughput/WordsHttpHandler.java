package com.androj.kata.multithreading.troughput;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class WordsHttpHandler implements HttpHandler {
    private String bookContent;

    public WordsHttpHandler(Path book_file_path) {
        try {
            this.bookContent = Files
                    .readString(book_file_path);
        } catch (IOException e) {
            System.out.println("Could not read book");
            this.bookContent = "";
        }
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String[] keyValue = httpExchange.getRequestURI().getQuery()
                .split("=");

        String action = keyValue[0];
        String param = keyValue[1];

        if (!action.equals("word")) {
            httpExchange.sendResponseHeaders(400, 0);
            return;
        }

        long wordCount = countWord(param);

        byte[] wordCountBytes = Long.toString(wordCount).getBytes();

        httpExchange.sendResponseHeaders(200, wordCountBytes.length);
        OutputStream responseBody = httpExchange.getResponseBody();
        responseBody.write(wordCountBytes);
        responseBody.close();
    }

    private long countWord(String param) {
        long count = 0;
        int index = 0;
        while (index >= 0) {
            index = this.bookContent.indexOf(param, index);
            if (index >= 0) {
                count++;
                index++;
            }
        }
        return count;
    }
}
