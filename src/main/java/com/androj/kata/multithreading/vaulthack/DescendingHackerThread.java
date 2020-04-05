package com.androj.kata.multithreading.vaulthack;

public class DescendingHackerThread extends AbstractHackerThread{

    public DescendingHackerThread(Vault vault) {
        super(vault);
    }

    @Override
    public void run() {
        for (int i = Vault.MAX_PASSWORD; i >=0; i--) {
            hack(i);
        }
    }
}
