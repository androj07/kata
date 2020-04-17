package com.androj.kata.multithreading.wait;

import com.androj.kata.multithreading.wait.matrix.MatrixFileWriter;
import com.androj.kata.multithreading.wait.matrix.MatrixPair;

public class MatricesMultiplierConsumer extends Thread {
    private final ThreadSafeQueue queue;
    private final MatrixFileWriter matrixFileWriter;

    public MatricesMultiplierConsumer(ThreadSafeQueue queue, MatrixFileWriter matrixFileWriter) {
        this.queue = queue;
        this.matrixFileWriter = matrixFileWriter;
    }

    @Override
    public void run() {
        while (true) {
            MatrixPair matrixPair = queue.remove();
            if (matrixPair == null) {
                System.out.println("No more work to do : " + getName());
                return;
            }
            float[][] resultMatrix = multiplyMatrices(matrixPair);
            matrixFileWriter.write(resultMatrix);
            matrixFileWriter.flush();
        }
    }

    private float[][] multiplyMatrices(MatrixPair matrixPair) {
        float[][] matrixA = matrixPair.getMatrixA();
        float[][] matrixB = matrixPair.getMatrixB();
        float[][] result = new float[matrixA.length][matrixB[0].length];

        for (int r = 0; r < matrixA.length; r++) {
            for (int c = 0; c < matrixB[0].length; c++) {
                for (int i = 0; i < matrixA.length; i++) {
                    result[r][c] = matrixA[r][i] * matrixB[i][c];
                }
            }
        }
        return result;
    }
}
