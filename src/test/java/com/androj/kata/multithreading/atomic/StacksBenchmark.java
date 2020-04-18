package com.androj.kata.multithreading.atomic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StacksBenchmark {
    private final int THREADS_IN_GROUP = 2;
    private final int ITERATION_DURATION_MILLIS = 1000;
    private final int ITERATIONS = 10;

    @Test
    public void benchmark() throws InterruptedException {

        System.out.println("Synchronized stack");
        for (int i = 0; i < ITERATIONS; i++) {
            SynchronizedStack<Integer> stack = new SynchronizedStack<>();
            List<PushingThread> pushing = new ArrayList<>();
            for (int j = 0; j < THREADS_IN_GROUP; j++) {
                pushing.add(new PushingThread(stack));
            }
            List<PoppingThread> popping = new ArrayList<>();
            for (int j = 0; j < THREADS_IN_GROUP; j++) {
                popping.add(new PoppingThread(stack));
            }

            pushing.forEach(Thread::start);
            popping.forEach(Thread::start);

            Thread.sleep(ITERATION_DURATION_MILLIS);
            System.out.println(String.format("Done operations : %,d",stack.getOperationsCount()));

            pushing.forEach(PushingThread::terminate);
            popping.forEach(PoppingThread::terminate);

            for (PushingThread pushingThread : pushing) {
                pushingThread.join();
            }

            for (PoppingThread poppingThread : popping) {
                poppingThread.join();
            }
        }

        System.out.println("Non-blocking stack");
        for (int i = 0; i < ITERATIONS; i++) {
            NonBlockingStack<Integer> stack = new NonBlockingStack<>();
            List<PushingThread> pushing = new ArrayList<>();
            for (int j = 0; j < THREADS_IN_GROUP; j++) {
                pushing.add(new PushingThread(stack));
            }
            List<PoppingThread> popping = new ArrayList<>();
            for (int j = 0; j < THREADS_IN_GROUP; j++) {
                popping.add(new PoppingThread(stack));
            }

            pushing.forEach(Thread::start);
            popping.forEach(Thread::start);

            Thread.sleep(ITERATION_DURATION_MILLIS);
            System.out.println(String.format("Done operations : %,d",stack.getOperationsCount()));

            pushing.forEach(PushingThread::terminate);
            popping.forEach(PoppingThread::terminate);

            for (PushingThread pushingThread : pushing) {
                pushingThread.join();
            }

            for (PoppingThread poppingThread : popping) {
                poppingThread.join();
            }
        }

    }

    private class PushingThread extends Thread {
        private final Stack<Integer> stack;
        private final Random random = new Random();
        private boolean keepGoing = true;

        private PushingThread(Stack<Integer> stack) {
            this.stack = stack;
            this.setDaemon(true);
        }

        @Override
        public void run() {
            while (keepGoing) {
                int next = random.nextInt();
                this.stack.push(next);
            }
        }

        public void terminate() {
            this.keepGoing = false;
        }
    }

    private class PoppingThread extends Thread {
        private final Stack<Integer> stack;
        private boolean keepGoing = true;

        private PoppingThread(Stack<Integer> stack) {
            this.stack = stack;
            this.setDaemon(true);
        }

        @Override
        public void run() {
            while (keepGoing) {
                this.stack.pop();
            }
        }

        public void terminate() {
            this.keepGoing = false;
        }
    }
}
