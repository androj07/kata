package com.androj.kata.multithreading.repaint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.ForkJoinPool;

public class MainBlurForkJoin {
    public static void main(String[] args) throws Exception {
        BufferedImage image = ImageIO.read(Path.of("in/multithreading/repaint/purple.jpg").toFile());
        BufferedImage outputImageConcurrent = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Colors colors = new Colors();
        ForkJoinPool pool = new ForkJoinPool();
        long concurrentStart = System.currentTimeMillis();
        pool.invoke(new ForkBlur(colors, image, outputImageConcurrent, 0, image.getWidth(), 0, image.getHeight()));
        long concurrentEnd = System.currentTimeMillis();
        long concurrentTime = (concurrentEnd - concurrentStart);
        System.out.println("Concurrent : " + concurrentTime);
        ImageIO.write(outputImageConcurrent, "jpg", new File("flowers-out-blur.jpg"));
    }
}
