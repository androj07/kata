package com.androj.kata.multithreading.synchronizedbuffer;

public class SynchronizedBuffer {
    private final int[] innerBuffer;
    private int innerIndex = -1;
    private boolean terminated = false;

    public SynchronizedBuffer(int capacity){
        this.innerBuffer = new int[capacity];
    }

    public synchronized void add(int value) {

        if (isFull()) {
            try {
                //System.out.println("Waiting for place");
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }
        innerIndex++;
        innerBuffer[innerIndex] = value;
        //System.out.println("Added " + value);

        notify();

    }

    public synchronized int remove() {
        if (isEmpty()) {
            try {
               // System.out.println("Waiting for new item");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        int value = innerBuffer[innerIndex];
        //System.out.println("Removed " + value);
        innerIndex--;
        notify();
        return value;

    }

    private boolean isFull() {
        return innerIndex == innerBuffer.length - 1;
    }

    private boolean isEmpty() {
        return innerIndex < 0;
    }

    public synchronized void terminate() {
        this.terminated = true;
        notifyAll();
    }

    public synchronized boolean hasMoreWork() {
        return !(terminated && isEmpty());
    }


}
