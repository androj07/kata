package com.androj.kata.multithreading.repaint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MainRepaint {
    private static Colors colors = new Colors();

    public static void main(String[] args) throws Exception {


        BufferedImage image = ImageIO.read(Path.of("in/multithreading/repaint/many-flowers.jpg").toFile());
        int height = image.getHeight();
        BufferedImage outputImageConcurrent = new BufferedImage(image.getWidth(), height, BufferedImage.TYPE_INT_RGB);

        int processors = Runtime.getRuntime().availableProcessors();

        int chunkHeight = height / processors;

        List<Thread> threads = new ArrayList<>();
        int currentHeight = height;
        while (currentHeight > 0) {
            int heightStart = Math.max(currentHeight - chunkHeight, 0);
            int endHeight = currentHeight;
            threads.add(new Thread(() -> {
                repaint(image, outputImageConcurrent, 0, image.getWidth(), heightStart, endHeight);
            }));
            currentHeight -= chunkHeight;
        }
        long concurrentStart = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        long concurrentEnd = System.currentTimeMillis();
        long concurrentTime = (concurrentEnd - concurrentStart);
        System.out.println("Concurrent : " + concurrentTime);
        ImageIO.write(outputImageConcurrent, "jpg", new File("flowers-out-concurrent.jpg"));

        BufferedImage outputImageSerial = new BufferedImage(image.getWidth(), height, BufferedImage.TYPE_INT_RGB);
        long serialStart = System.currentTimeMillis();
        repaint(image, outputImageSerial, 0, image.getWidth(), 0, image.getHeight());
        long serialEnd = System.currentTimeMillis();
        long serialTime = (serialEnd - serialStart);
        System.out.println("Serial : " + serialTime);

        System.out.println("Faster : "+ ((((double)serialTime/concurrentTime)-1)*100) + " %");

        ImageIO.write(outputImageConcurrent, "jpg", new File("flowers-out-serial.jpg"));

    }


    private static void repaint(BufferedImage inImage, BufferedImage outImage, int xStart, int xEnd, int yStart, int yEnd) {
        for (int x = xStart; x < xEnd; x++) {
            for (int y = yStart; y < yEnd; y++) {
                int pixel = inImage.getRGB(x, y);
                int outPixel = colors.makePurpleIfShadeOfGray(pixel);
                outImage.setRGB(x, y, outPixel);
            }
        }
    }
}
