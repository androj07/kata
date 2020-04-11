package com.androj.kata.readwritelock;

import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Demonstrates usage of ReentrantReadWriteLock
 */
public class InventoryDatabase {
    public final static int MAX_PRICE = 1000;
    private final TreeMap<Integer, Integer> priceToCount = new TreeMap<>();
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public int getNumberOfItemsInPriceRange(int lowerBound, int upperBound) {
        readLock.lock();
        try {
            Integer lowerPriceKey = priceToCount.ceilingKey(lowerBound);
            Integer upperPriceKey = priceToCount.floorKey(upperBound);
            if (lowerPriceKey == 0 || upperPriceKey == 0) {
                return 0;
            }

            NavigableMap<Integer, Integer> pricesInRange = priceToCount.subMap(lowerPriceKey, true, upperPriceKey, true);

            return pricesInRange.values()
                    .stream()
                    .reduce(Integer::sum)
                    .orElse(0);
        } finally {
            readLock.unlock();
        }
    }

    public void addItem(int price) {
        writeLock.lock();
        priceToCount.compute(price, (itemPrice, count) -> {
            if (count == null) {
                count = 0;
            }
            count++;
            return count;
        });
        writeLock.unlock();
    }

    public void removeItem(int price) {
        writeLock.lock();
        Integer countOfItemsWithPrice = priceToCount.get(price);
        if (countOfItemsWithPrice == null) {
            return;
        }
        if (countOfItemsWithPrice == 1) {
            priceToCount.remove(price);
        } else {
            priceToCount.put(price, countOfItemsWithPrice - 1);
        }
        writeLock.unlock();
    }
}
