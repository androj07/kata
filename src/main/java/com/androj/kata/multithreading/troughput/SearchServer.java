package com.androj.kata.multithreading.troughput;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchServer {

    private HttpServer server;
    private final Path BOOK_FILE_PATH = Path.of("in/multithreading/troughput/war_and_peace.txt");
    private final ExecutorService requestsExecutor = Executors.newFixedThreadPool(11);

    public SearchServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(8000), 0);
        } catch (IOException e) {
            throw new RuntimeException("Server can not start");
        }
        this.server.createContext("/search", new WordsHttpHandler(BOOK_FILE_PATH));
        this.server.setExecutor(this.requestsExecutor);
    }

    public void start() {
        this.server.start();
    }
}
