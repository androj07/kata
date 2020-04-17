package com.androj.kata.multithreading.wait.matrix;

import java.util.Random;

public class MatrixGenerator {
    private final Random random = new Random();

    public float[][] generateRandomMatrix(int columns, int rows){
        float[][] matrix = new float[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = ((random.nextFloat()*100));
            }
        }
        return matrix;
    }
}
