package com.androj.kata.multithreading.atomic;

public class StackNode<T> {
    private final T value;
    private StackNode<T> next;

    public StackNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public StackNode<T> getNext() {
        return next;
    }

    public void setNext(StackNode<T> next) {
        this.next = next;
    }
}
