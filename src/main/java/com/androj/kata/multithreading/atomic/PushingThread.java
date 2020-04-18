package com.androj.kata.multithreading.atomic;

import java.util.Random;

public class PushingThread extends Thread {
    private final Stack<Integer> stack;
    private final Random random = new Random();

    public PushingThread(Stack<Integer> stack) {
        this.stack = stack;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            int next = random.nextInt();
            this.stack.push(next);
        }
    }
}

