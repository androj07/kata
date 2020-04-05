package com.androj.kata.multithreading.metrics;

public class MetricsPrinter {
    private Metrics metrics;

    public MetricsPrinter(Metrics metrics) {
        this.metrics = metrics;
    }

    public void print() {
        System.out.println(metrics.getAverage());
    }
}
