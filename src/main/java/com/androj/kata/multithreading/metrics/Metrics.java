package com.androj.kata.multithreading.metrics;

public class Metrics {
    private long count = 0;
    private volatile double average = 0.0;

    public synchronized void addSample(long sample){
        double sum = count*average;
        sum += sample;
        count++;
        average = sum/count;
    }

    public double getAverage() {
        return average;
    }
}
