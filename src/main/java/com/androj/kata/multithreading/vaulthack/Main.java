package com.androj.kata.multithreading.vaulthack;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int password = new Random().nextInt(Vault.MAX_PASSWORD);
        Vault vault = new Vault(password);

        Thread ascendingHackerThread = new AscendingHackerThread(vault);
        Thread descendingHackerThread = new DescendingHackerThread(vault);
        Thread policeThread = new PoliceThread();

        ascendingHackerThread.start();
        descendingHackerThread.start();
        policeThread.start();
    }
}
