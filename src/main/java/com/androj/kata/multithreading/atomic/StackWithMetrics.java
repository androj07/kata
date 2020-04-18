package com.androj.kata.multithreading.atomic;

public interface StackWithMetrics<T> extends Stack<T> {
    int getOperationsCount();
}
