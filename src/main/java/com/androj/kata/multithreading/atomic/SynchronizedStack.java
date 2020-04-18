package com.androj.kata.multithreading.atomic;

import java.util.ArrayList;
import java.util.List;

public class SynchronizedStack<T> implements StackWithMetrics<T> {
    private int operations = 0;
    private StackNode<T> head = null;

    @Override
    public int getOperationsCount() {
        return operations;
    }

    @Override
    public void push(T item) {
        synchronized (this) {
            StackNode<T> current = new StackNode<>(item);
            current.next = head;
            head = current;
            operations++;
        }
    }

    @Override
    public T pop() {
        synchronized (this) {
            operations++;
            if (head == null) {
                return null;
            }
            StackNode<T> lastHead = head;
            head = head.next;
            return lastHead.getValue();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StackWithMetrics<Integer> stack = new SynchronizedStack<>();
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
        System.out.println(String.format("Done operations : %,d", stack.getOperationsCount()));

    }


}
