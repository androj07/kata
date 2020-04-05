package com.androj.kata.multithreading.metrics;

public class MetricsMain {

    public static void main(String[] args) {
        Metrics metrics = new Metrics();

        BusinessLogic businessLogic1 = new BusinessLogic(metrics);
        BusinessLogic businessLogic2 = new BusinessLogic(metrics);

        businessLogic1.start();
        businessLogic2.start();

        MetricsPrinter metricsPrinter = new MetricsPrinter(metrics);
        Thread metricsPrinterThread = new Thread(() -> {
            while (true) {
                metricsPrinter.print();
            }
        });
        metricsPrinterThread.start();
    }
}
