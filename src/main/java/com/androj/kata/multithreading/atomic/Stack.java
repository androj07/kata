package com.androj.kata.multithreading.atomic;

public interface Stack<T> {
    void push(T item);
    T pop();
}
