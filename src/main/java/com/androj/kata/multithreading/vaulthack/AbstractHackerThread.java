package com.androj.kata.multithreading.vaulthack;

public abstract class AbstractHackerThread extends Thread{
    protected final Vault vault;

    public AbstractHackerThread(Vault vault) {
        this.vault = vault;
    }

    @Override
    public synchronized void start() {
        this.setName(this.getClass().getSimpleName());
        System.out.println("Starting "+this.getName());
        super.start();
    }

    protected void hack(int combination){
        if(vault.evaluatePassword(combination)){
            System.out.println(this.getName() + " hacked vault with combination " + combination);
            System.exit(0);
        }
    }
}
