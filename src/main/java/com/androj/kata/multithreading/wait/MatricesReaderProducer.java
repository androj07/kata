package com.androj.kata.multithreading.wait;

import com.androj.kata.multithreading.wait.matrix.MatrixPair;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatricesReaderProducer extends Thread {
    private final ThreadSafeQueue queue;
    private final Scanner scanner;

    public MatricesReaderProducer(ThreadSafeQueue queue, FileReader matrixFileReader) throws IOException {
        this.queue = queue;
        this.scanner = new Scanner(matrixFileReader);
    }

    @Override
    public void run() {
        while (true) {
            float[][] first = readMatrix();
            float[][] second = readMatrix();
            if (first == null || second == null) {
                queue.terminate();
                System.out.println("Producer stopped to produce");
                return;
            }
            MatrixPair pair = new MatrixPair();
            pair.setMatrixA(first);
            pair.setMatrixB(second);
            queue.put(pair);
        }
    }

    private float[][] readMatrix() {
        List<String> matrixRows = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line == null || line.isEmpty()) {
                break;
            }
            matrixRows.add(line);
        }

        int rows = matrixRows.size();
        if (rows == 0) {
            return null;
        }
        int columns = matrixRows.get(0).split(",").length;
        float[][] matrix = new float[rows][columns];
        for (int r = 0; r < rows; r++) {
            String[] values = matrixRows.get(r).split(",");
            for (int c = 0; c < columns; c++) {
                matrix[r][c] = Float.parseFloat(values[c]);
            }
        }
        return matrix;

    }
}
