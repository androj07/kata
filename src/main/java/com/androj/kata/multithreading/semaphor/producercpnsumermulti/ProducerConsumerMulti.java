package com.androj.kata.multithreading.semaphor.producercpnsumermulti;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerMulti<T> {
    private final Semaphore full = new Semaphore(0);
    private final Semaphore empty;
    private Queue<T> queue = new ArrayDeque<>();
    private Lock lock = new ReentrantLock();

    private Thread queueSizeObserver;

    public ProducerConsumerMulti(int capacity) {
        empty = new Semaphore(capacity);

        queueSizeObserver = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("Queue size %d", queue.size()));
            }
        });

        queueSizeObserver.setDaemon(true);
        queueSizeObserver.start();
    }

    public void produce(T item) throws InterruptedException {
        empty.acquire();
        lock.lock();
        queue.offer(item);
        lock.unlock();
        full.release();
    }

    public T consume() throws InterruptedException {
        full.acquire();
        lock.lock();
        T item = queue.poll();
        lock.unlock();
        empty.release();
        return item;
    }

    public List<T> drain() {
        List<T> remaining = new ArrayList<>(queue.size());
        queue.retainAll(remaining);
        return remaining;
    }


}
