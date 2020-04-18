package com.androj.kata.multithreading.atomic;

public class PoppingThread extends Thread {
    private final Stack<Integer> stack;

    public PoppingThread(Stack<Integer> stack) {
        this.stack = stack;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            this.stack.pop();
        }
    }

}