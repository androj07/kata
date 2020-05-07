package com.androj.kata.bytes;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class KeysTest {

    @Test
    public void bothKeysShouldBeEqual(){
        short left = (short)123;
        int right = 1024;

        long keyWithByteBuffer = Keys.createKeyWithByteBuffer(left, right);
        System.out.println(keyWithByteBuffer);
        long keyLean = Keys.createKeyLean(left, right);
        System.out.println(keyLean);
        assertTrue(keyLean==keyWithByteBuffer);
    }

    @Test
    public void performanceByteBuffer(){
        Random random = new Random();
        int transactionDates = 356;
        int users = 1_000_000;
        for (int k = 0; k < 10; k++) {
            long start = System.currentTimeMillis();
            for (long i = 0; i < transactionDates ; i++) {
                for (int j = 0; j < users; j++) {
                    short left = (short) (random.nextInt() % Short.MAX_VALUE);
                    int right = random.nextInt();
                    long keyWithByteBuffer = Keys.createKeyWithByteBuffer(left, right);
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("Byte,"+(end - start));
        }

    }

    @Test
    public void performanceLean(){
        Random random = new Random();
        int transactionDates = 356;
        int users = 1_000_000;
        for (int k = 0; k < 10; k++) {
            long start = System.currentTimeMillis();
            for (long i = 0; i < transactionDates; i++) {
                for (int j = 0; j < users; j++) {
                    short left = (short) (random.nextInt() % Short.MAX_VALUE);
                    int right = random.nextInt();
                    long keyLean = Keys.createKeyLean(left, right);
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("Lean," + (end - start));
        }
    }

}