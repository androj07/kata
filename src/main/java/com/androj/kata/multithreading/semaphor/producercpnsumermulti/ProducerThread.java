package com.androj.kata.multithreading.semaphor.producercpnsumermulti;

import java.util.Random;
import java.util.function.Supplier;

public class ProducerThread<T> extends Thread{
    private final ProducerConsumerMulti<T> producerConsumerMulti;
    private final Supplier<T> messageSupplier;
    private final Random random = new Random();
    public ProducerThread(ProducerConsumerMulti<T> producerConsumerMulti, Supplier<T> messageSupplier) {
        this.producerConsumerMulti = producerConsumerMulti;
        this.messageSupplier = messageSupplier;
    }

    @Override
    public void run() {
        try {
            for (int j = 0; j < 10000; j++) {
                sleep(random.nextInt(50));
                producerConsumerMulti.produce(messageSupplier.get());
            }
        } catch (InterruptedException e) {
            System.out.println(String.format("Thread %s interrupted", this.getName()));
            return;
        }
    }

}
