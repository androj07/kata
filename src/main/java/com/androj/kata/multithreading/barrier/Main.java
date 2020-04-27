package com.androj.kata.multithreading.barrier;

import java.util.concurrent.Phaser;

public class Main {

    public static void main(String[] args) {
        int threads = 10;
        Phaser phaser = new Phaser(threads);

        for (int i = 0; i < threads; i++) {
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName()+" started");
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName()+" working");
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName()+" finished");
            }).start();
        }
    }
}
