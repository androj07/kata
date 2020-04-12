package com.androj.kata.multithreading.semaphor.producercpnsumermulti;

import java.util.Random;
import java.util.function.Consumer;

public class ConsumerThread<T> extends Thread {
    private final ProducerConsumerMulti<T> producerConsumerMulti;
    private final Consumer<T> messageConsumer;
    private final Random random = new Random();
    public ConsumerThread(ProducerConsumerMulti<T> producerConsumerMulti, Consumer<T> messageConsumer) {
        this.producerConsumerMulti = producerConsumerMulti;
        this.messageConsumer = messageConsumer;
    }

    @Override
    public void run() {
        T message = null;
        try {
            message = producerConsumerMulti.consume();
        } catch (InterruptedException e) {
            System.out.println(String.format("Consumer thread %s interrupted", this.getName()));
            return;
        }
        while (message != null) {
            try {
                sleep(random.nextInt(50));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           // messageConsumer.accept(message);
            try {
                message = producerConsumerMulti.consume();
            } catch (InterruptedException e) {
                System.out.println(String.format("Consumer thread %s interrupted", this.getName()));
                return;
            }
        }
    }
}
