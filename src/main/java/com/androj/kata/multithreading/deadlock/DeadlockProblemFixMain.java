package com.androj.kata.multithreading.deadlock;

import java.util.Random;

public class DeadlockProblemFixMain {

    public static void main(String[] args) {
        Intersection intersection = new Intersection();
        new Thread(new TrainA(intersection)).start();
        new Thread(new TrainB(intersection)).start();
    }

    public static class TrainA implements Runnable{

        private final Intersection intersection;

        public TrainA(Intersection intersection) {
            this.intersection = intersection;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(5));
            } catch (InterruptedException e) {
            }
            while (true) {
                intersection.passRailA();
            }
        }
    }

    public static class TrainB implements Runnable{

        private final Intersection intersection;

        public TrainB(Intersection intersection) {
            this.intersection = intersection;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(5));
            } catch (InterruptedException e) {
            }
            while (true) {
                intersection.passRailB();
            }
        }
    }


    public static class Intersection {
        Object railA = new Object();
        Object railB = new Object();

        public void passRailA() {
            synchronized (railA) {
                System.out.println(Thread.currentThread().getName()+" locked rail A to pass safely");
                synchronized (railB) {
                    System.out.println(Thread.currentThread().getName()+" passing");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }


        public void passRailB() {
            //Fix : changed the order of locking to be the same in both methods.
            // If the threads will try to acquire locks in the same order then the deadlock will not happen
            synchronized (railA) {
                System.out.println(Thread.currentThread().getName()+" locked rail A to pass safely");
                synchronized (railB) {
                    System.out.println(Thread.currentThread().getName()+" passing");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }
}
