package com.androj.kata.multithreading.threadinterruption;

import java.math.BigInteger;

public class MainInterruptedExplicitly {
    public static void main(String[] args) {
        Thread thread = new Thread(new LongCalculationTask(new BigInteger("200000"),new BigInteger("10000000000")));
        thread.start();

        thread.interrupt();
    }

    public static class LongCalculationTask implements Runnable {

        private final BigInteger base;
        private final BigInteger pow;

        public LongCalculationTask(BigInteger base, BigInteger pow) {
            this.base = base;
            this.pow = pow;
        }

        @Override
        public void run() {
            System.out.println(base + " ^ " + pow + " = " + this.calculatePow());
        }

        private BigInteger calculatePow() {
            BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(pow) < 0; i = i.add(BigInteger.ONE)) {
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Calculation interrupted");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }
            return result;
        }
    }

}
