package com.androj.kata.multithreading.wait;

import com.androj.kata.multithreading.wait.matrix.MatrixFileWriter;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        ThreadSafeQueue threadSafeQueue = new ThreadSafeQueue();
        FileReader matricesFileReader = new FileReader(Path.of("./in/matrices/matrices").toFile());
        MatricesReaderProducer matricesReaderProducer = new MatricesReaderProducer(threadSafeQueue,matricesFileReader);
        matricesReaderProducer.start();

        MatrixFileWriter matrixFileWriter = new MatrixFileWriter(Path.of("./in/matrices/matrices_multiplied"));
        MatricesMultiplierConsumer matricesMultiplierConsumer = new MatricesMultiplierConsumer(threadSafeQueue,matrixFileWriter);
        matricesMultiplierConsumer.start();

        matricesMultiplierConsumer.join();
        matrixFileWriter.close();

    }
}
