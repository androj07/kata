package com.androj.kata.multithreading.wait.matrix;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.StringJoiner;

public class MatrixFileWriter {
    private BufferedWriter writer;

    public MatrixFileWriter(Path path) throws IOException {
        this.writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void write(float[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            StringJoiner joiner = new StringJoiner(",");
            for (int column = 0; column < matrix[row].length; column++) {
                joiner.add(String.valueOf(matrix[row][column]));
            }
            try {
                writer.write(joiner.toString() + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            writer.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        if (writer != null) {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void flush(){
        if(writer!=null){
            try {
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
