package com.androj.kata.multithreading.vaulthack;

public class Vault {
    public static int MAX_PASSWORD  = 9999;
    private final int password;

    public Vault(int password) {
        this.password = password;
    }

    public boolean evaluatePassword(int password){
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
        }

        return this.password == password;
    }
}
