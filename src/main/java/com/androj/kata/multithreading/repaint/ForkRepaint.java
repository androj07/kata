package com.androj.kata.multithreading.repaint;

import java.awt.image.BufferedImage;
import java.util.concurrent.RecursiveAction;

public class ForkRepaint extends RecursiveAction {
    private Colors colors;
    private BufferedImage inImage;
    private BufferedImage outImage;
    private int xStart;
    private int xEnd;
    private int yStart;
    private int yEnd;

    public ForkRepaint(Colors colors, BufferedImage inImage, BufferedImage outImage, int xStart, int xEnd, int yStart, int yEnd) {
        this.colors = colors;
        this.inImage = inImage;
        this.outImage = outImage;
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.yEnd = yEnd;
    }

    @Override
    protected void compute() {
        int yDelta = (yEnd - yStart) + 1;
        int xDelta = (xEnd - xStart) + 1;
        if (yDelta <= 100 || xDelta <= 100) {
            for (int x = xStart; x < xEnd; x++) {
                for (int y = yStart; y < yEnd; y++) {
                    int pixel = inImage.getRGB(x, y);
                    int outPixel = colors.makePurpleIfShadeOfGray(pixel);
                    outImage.setRGB(x, y, outPixel);
                }
            }
        } else {
            int yMid = (yStart+yEnd)>>>1;
            int xMid = (xStart+xEnd)>>>1;
            invokeAll(
                    new ForkRepaint(colors,inImage,outImage,xStart,xMid,yStart,yMid),
                    new ForkRepaint(colors,inImage,outImage,xMid,xEnd,yStart,yMid),
                    new ForkRepaint(colors,inImage,outImage,xStart,xMid,yMid,yEnd),
                    new ForkRepaint(colors,inImage,outImage,xMid,xEnd,yMid,yEnd)
            );
        }
    }
}
