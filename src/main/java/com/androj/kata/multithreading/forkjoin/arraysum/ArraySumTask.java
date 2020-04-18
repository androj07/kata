package com.androj.kata.multithreading.forkjoin.arraysum;

import java.util.concurrent.RecursiveAction;

public class ArraySumTask extends RecursiveAction {
    private final int[] array;
    private final int indexMin;
    private final int indexMax;
    private long result = 0;

    public ArraySumTask(int[] array, int indexMin, int indexMax) {
        this.array = array;
        this.indexMin = indexMin;
        this.indexMax = indexMax;
    }

    @Override
    protected void compute() {

        if (indexMin > indexMax) result = 0;
        else if (indexMin == indexMax) result = array[indexMin];
        else {
            int indexMid = (indexMin + indexMax) >>> 1;

            for (int i = indexMin; i <= indexMax; i++) {
                result += array[i];
            }
            ArraySumTask left = new ArraySumTask(array, indexMin, indexMid);
            ArraySumTask right = new ArraySumTask(array, indexMid + 1, indexMax);

            invokeAll(left, right);
            result = left.result + right.result;
        }
    }

    public long getResult() {
        return result;
    }
}
