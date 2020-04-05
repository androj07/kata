package com.androj.kata.multithreading.troughput;

public class TroughputMain {

    public static void main(String[] args) {

        SearchServer searchserver = new SearchServer();
        searchserver.start();
    }
}
