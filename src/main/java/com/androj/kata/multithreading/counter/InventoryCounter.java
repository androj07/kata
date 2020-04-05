package com.androj.kata.multithreading.counter;

public class InventoryCounter {

    public static void main(String[] args) throws InterruptedException {
        Inventory inventory = new Inventory();

        Thread increaser = new Thread(()->{
            for (int i = 0 ; i < 1000000; i++) {
                inventory.increase();
            }
        });
        increaser.start();

        Thread decreaser = new Thread(()->{
            for (int i = 0 ; i < 1000000; i++) {
                inventory.decrease();
            }
        });
        decreaser.start();

        increaser.join();
        decreaser.join();

        System.out.println("On stock : "+inventory.getOnStock());

    }

    public static class Inventory{
        private long onStock = 0;

        public synchronized void increase(){
            this.onStock++;
        }

        public synchronized void decrease(){
            this.onStock--;
        }

        public long getOnStock() {
            return onStock;
        }
    }
}
