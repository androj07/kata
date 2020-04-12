package com.androj.kata.multithreading.semaphor.producerconsumersingle;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerSingle<LocalDateTime> producerConsumerSingle = new ProducerConsumerSingle<>(
                LocalDateTime::now,
                System.out::println
        );

        producerConsumerSingle.start();


        //Thread.sleep(2000);

        //producerConsumerSingle.stop();


    }
}
