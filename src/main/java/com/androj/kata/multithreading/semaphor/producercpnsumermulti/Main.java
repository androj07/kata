package com.androj.kata.multithreading.semaphor.producercpnsumermulti;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerMulti<String> producerConsumerMulti = new ProducerConsumerMulti<>(5);

        Supplier<String> messageSupplier = () -> Thread.currentThread().getName()+"-"+LocalDateTime.now();
        Consumer<String> messageConsumer = (message) ->{
            System.out.println(message+"-"+Thread.currentThread().getName());
        };

        List<Thread> producers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            producers.add(new ProducerThread<>(producerConsumerMulti,messageSupplier));
        }

        producers.forEach(Thread::start);

        List<Thread> consumers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            consumers.add(new ConsumerThread<>(producerConsumerMulti,messageConsumer));
        }

        consumers.forEach(Thread::start);


        for (Thread producer : producers) {
            producer.join();
        }

        for (Thread consumer : consumers) {
            consumer.join();
        }


    }
}
