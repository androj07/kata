package com.androj.kata.multithreading.atomic;

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
            StackNode<T> current = new StackNode<>();
            current.setValue(item);
            current.setNext(head);
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
            head = head.getNext();
            return lastHead.getValue();
        }
    }


}
