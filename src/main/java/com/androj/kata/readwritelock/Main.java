package com.androj.kata.readwritelock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        InventoryDatabase inventoryDatabase = new InventoryDatabase();
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int testPrice = random.nextInt(InventoryDatabase.MAX_PRICE);
            inventoryDatabase.addItem(testPrice);
        }


        InventoryUpdaterThread inventoryUpdaterThread = new InventoryUpdaterThread(inventoryDatabase);
        inventoryUpdaterThread.setDaemon(true);

        List<Thread> readers = new ArrayList<>();
        for (int i = 0; i < Runtime.getRuntime().availableProcessors()-2; i++) {
            readers.add(new InventoryReader(inventoryDatabase));
        }

        long start = System.currentTimeMillis();
        inventoryUpdaterThread.start();
        for (Thread reader : readers) {
            reader.start();
        }

        for (Thread reader : readers) {
            reader.join();
        }

        long stop = System.currentTimeMillis();
        System.out.println(String.format("Finished in %d millis",(stop - start)));

    }
}
