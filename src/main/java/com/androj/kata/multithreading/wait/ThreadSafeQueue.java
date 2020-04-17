package com.androj.kata.multithreading.wait;

import com.androj.kata.multithreading.wait.matrix.MatrixPair;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadSafeQueue {
    private Queue<MatrixPair> queue = new LinkedList<>();
    private boolean isEmpty = true;
    private boolean shouldTerminate = false;

    public synchronized void put(MatrixPair matrixPair){
        queue.add(matrixPair);
        isEmpty = false;
        // If any consumer thread is waiting for work
        // wake him up to recheck the queue and take new amount of work
        notify();
    }

    public synchronized MatrixPair remove(){
        while (isEmpty && !shouldTerminate){
            //if the queue is empty but not marked to terminate
            // then the consumer should wait for new amount of work to come
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(queue.size() == 1){
            // if the queue has only 1 element, then when
            // we will remove this element the queue will be empty
            isEmpty = true;
        }

        if(isEmpty && shouldTerminate){
            // this means that the producer said that he finished producing
            // and the consumers have drained the queue. Nothing to od here anymore.
            return null;
        }

        System.out.println("Queue size : "+queue.size());

        return queue.remove();
    }

    public synchronized void terminate(){
        this.shouldTerminate = true;
        // wake up all potentially waiting consumer threads
        notifyAll();
    }
}
