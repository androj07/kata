package com.androj.kata.multithreading.wait.matrix;

import java.io.IOException;
import java.nio.file.Path;

public class GeneratorMain {
    private final static int COLUMNS_AND_ROWS = 10;
    public static void main(String[] args) throws IOException {

        MatrixGenerator matrixGenerator = new MatrixGenerator();
        MatrixFileWriter matrixFileWriter = new MatrixFileWriter(Path.of("./in/matrices/matrices"));

        for (int i = 0; i < 100_000; i++) {
            float[][] matrix = matrixGenerator.generateRandomMatrix(COLUMNS_AND_ROWS, COLUMNS_AND_ROWS);
            matrixFileWriter.write(matrix);
        }

        matrixFileWriter.close();

    }
}
