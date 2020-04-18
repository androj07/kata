package com.androj.kata.multithreading.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class NonBlockingStack<T> implements StackWithMetrics<T> {
    private AtomicReference<StackNode<T>> head = new AtomicReference<>();
    private AtomicInteger operations = new AtomicInteger();

    @Override
    public int getOperationsCount() {
        return operations.get();
    }

    @Override
    public void push(T item) {
        StackNode<T> newHead = new StackNode<>();
        newHead.setValue(item);
        while (true) {
            StackNode<T> lastHead = head.get();
            newHead.setNext(lastHead);
            if (head.compareAndSet(lastHead, newHead)) {
                operations.incrementAndGet();
                break;
            } else {
                LockSupport.parkNanos(1);
            }
        }
    }

    @Override
    public T pop() {
        while (true) {
            StackNode<T> lastHead = head.get();
            StackNode<T> newHead = lastHead == null ? null : lastHead.getNext();
            if (head.compareAndSet(lastHead, newHead)) {
                operations.incrementAndGet();
                return lastHead == null ? null : lastHead.getValue();
            } else {
                LockSupport.parkNanos(1);
            }
        }
    }
}
