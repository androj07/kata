package com.androj.kata.multithreading.semaphor.producerconsumersingle;

import java.util.concurrent.Semaphore;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ProducerConsumerSingle<T> {
    private final Semaphore full = new Semaphore(0);
    private final Semaphore empty = new Semaphore(1);
    private T item = null;

    private final Thread producingThread;
    private final Thread consumingThread;

    private volatile boolean keepProducing = true;
    private volatile boolean keepConsuming = true;

    public ProducerConsumerSingle(Supplier<T> producingFunction, Consumer<T> consumingFunction) {

        this.producingThread = new Thread(() -> {
            while (keepProducing) {
                try {
                    //Simulate some hard work
                    Thread.sleep(1000);
                    empty.acquire();
                } catch (InterruptedException e) {
                    System.out.println("Producer interrupted");
                    return;
                }
                item = producingFunction.get();
                full.release();
            }
        });

        this.consumingThread = new Thread(() -> {
            while (keepConsuming) {
                try {
                full.acquire();
                } catch (InterruptedException e) {
                    System.out.println("Consumer interrupted");
                    return;
                }
                consumingFunction.accept(item);
                item = null;
                empty.release();
            }
        });

    }

    public void start() {
        this.producingThread.start();
        this.consumingThread.start();
    }

    public void stop() {
        this.keepProducing = false;
        this.keepConsuming = false;

        try {
            this.producingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            this.consumingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
