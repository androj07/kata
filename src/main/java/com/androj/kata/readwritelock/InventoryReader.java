package com.androj.kata.readwritelock;

import java.util.Random;

public class InventoryReader extends Thread{
    private final InventoryDatabase inventoryDatabase;
    private final Random random = new Random();
    public InventoryReader(InventoryDatabase inventoryDatabase) {
        this.inventoryDatabase = inventoryDatabase;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            int upperBound = random.nextInt(InventoryDatabase.MAX_PRICE);
            int lowerBound = upperBound == 0 ? 0 : random.nextInt(upperBound);
            inventoryDatabase.getNumberOfItemsInPriceRange(lowerBound,upperBound);
        }
    }
}
