package com.androj.kata.multithreading.threadinterruption;

public class MainInterruptedImplicitly {
    public static void main(String[] args) {
        Thread thread = new Thread(new LongRunningTask());
        thread.start();
        thread.interrupt();
    }

    public static class LongRunningTask implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted before it has finished");
            }
        }
    }
}
