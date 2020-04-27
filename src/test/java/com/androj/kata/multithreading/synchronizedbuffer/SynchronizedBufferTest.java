package com.androj.kata.multithreading.synchronizedbuffer;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class SynchronizedBufferTest {

    @Test
    public void consistencyTest() throws Exception{
        int repeats = 100;
        for (int r = 0; r < repeats; r++) {
            System.out.println("Repeat "+r);
            int capacity = 1_000;
            SynchronizedBuffer synchronizedBuffer = new SynchronizedBuffer(capacity);


            Thread producer = new Thread(() -> {
                for (int i = 0; i < capacity; i++) {
                    synchronizedBuffer.add(i);
                }
                synchronizedBuffer.terminate();
            });
            producer.start();

            Thread.sleep(100);

            boolean[] checklist = new boolean[capacity];
            Arrays.fill(checklist, false);
            Thread consumer = new Thread(() -> {
                while (synchronizedBuffer.hasMoreWork()) {
                    int value = synchronizedBuffer.remove();
                    checklist[value] = true;

                }
            });

            consumer.start();

            producer.join();
            consumer.join();

            for (int i = 0; i < checklist.length; i++) {
                assertTrue(checklist[i]);
            }
        }

    }

}