package com.androj.kata.multithreading.readwritelock;

import java.util.Random;

public class InventoryUpdaterThread extends Thread {

    private final InventoryDatabase inventoryDatabase;
    private final Random random = new Random();

    public InventoryUpdaterThread(InventoryDatabase inventoryDatabase) {
        this.inventoryDatabase = inventoryDatabase;
    }

    @Override
    public void run() {
        while (true) {
            int priceToAdd = random.nextInt(InventoryDatabase.MAX_PRICE);
            inventoryDatabase.addItem(priceToAdd);
            int priceToRemove = random.nextInt(InventoryDatabase.MAX_PRICE);
            inventoryDatabase.removeItem(priceToRemove);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
        }
    }
}
