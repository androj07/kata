package com.androj.kata.multithreading.atomic;

public class StackNode<T> {
    private T value;
    public StackNode<T> next;

    public StackNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
