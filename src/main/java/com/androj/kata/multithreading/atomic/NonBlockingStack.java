package com.androj.kata.multithreading.atomic;

import java.util.ArrayList;
import java.util.List;
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
        StackNode<T> newHead = new StackNode<>(item);
        while (true) {
            StackNode<T> lastHead = head.get();
            newHead.next = lastHead;
            if (head.compareAndSet(lastHead, newHead)) {
                break;
            } else {
                LockSupport.parkNanos(1);
            }
        }
        operations.incrementAndGet();
    }

    @Override
    public T pop() {
        StackNode<T> lastHead = head.get();
        StackNode<T> newHead;
        while (lastHead != null) {
            newHead = lastHead.next;
            if (head.compareAndSet(lastHead, newHead)) {
                break;
            } else {
                LockSupport.parkNanos(1);
                lastHead = head.get();
            }
        }
        operations.incrementAndGet();
        return lastHead != null ? lastHead.getValue() : null;
    }

    public static void main(String[] args) throws InterruptedException {
        StackWithMetrics<Integer> stack = new NonBlockingStack<>();
        List<PushingThread> pushing = new ArrayList<>();
        for (int j = 0; j < 1; j++) {
            pushing.add(new PushingThread(stack));
        }
        List<PoppingThread> popping = new ArrayList<>();
        for (int j = 0; j < 1; j++) {
            popping.add(new PoppingThread(stack));
        }

        pushing.forEach(Thread::start);
        popping.forEach(Thread::start);

        Thread.sleep(10000);
        System.out.println(String.format("Done operations : %,d",stack.getOperationsCount()));

    }
}
