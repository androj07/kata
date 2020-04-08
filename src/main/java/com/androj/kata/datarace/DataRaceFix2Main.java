package com.androj.kata.datarace;

public class DataRaceFix2Main {
    public static void main(String[] args) {

        SharedData sharedData = new SharedData();

        Thread incrementor = new Thread(() ->{
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedData.increment();
            }
        });

        Thread checker = new Thread(()->{
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedData.checkDataConsistency();
            }
        });

        incrementor.start();
        checker.start();

    }

    public static class SharedData{
        private int x = 0;
        private int y = 0;

        public synchronized void increment(){
            x++;
            y++;
        }

        public void checkDataConsistency(){
            if(y>x){
                System.out.println(" y > x : Data race detected");
            }
        }
    }
}
