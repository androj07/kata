package com.androj.kata.multithreading.vaulthack;

public class AscendingHackerThread extends AbstractHackerThread{

    public AscendingHackerThread(Vault vault) {
        super(vault);
    }

    @Override
    public void run() {
        for (int i = 0; i <= Vault.MAX_PASSWORD; i++) {
           hack(i);
        }
    }
}
