package com.androj.kata.multithreading.forkjoin.arraysum;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class ASum {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] values = new int[100_000_000];
        for (int i = 0; i < values.length; i++) {
            values[i] = (i+1);
        }

        ArraySumTask calculateArray = new ArraySumTask(values, 0, values.length - 1);
        long start = System.currentTimeMillis();
        pool.invoke(calculateArray);
        long end = System.currentTimeMillis();
        System.out.println(String.format("Result is %,d", calculateArray.getResult()));
        System.out.println(String.format("Computation time is %,d", (end - start)));

        long start2 = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        long end2 = System.currentTimeMillis();
        System.out.println(String.format("Result is %,d", sum));
        System.out.println(String.format("Serial computation time is %,d", (end2- start2)));
    }
}
