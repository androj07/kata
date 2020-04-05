package com.androj.kata.multithreading.vaulthack;

public class PoliceThread extends Thread {
    @Override
    public void run() {
        for (int i = 10; i > 0; i--) {
            System.out.println("Police will arrive in " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Police arrived, game over");
        System.exit(0);
    }
}
