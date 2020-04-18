package com.androj.kata.multithreading.repaint;

import java.awt.image.BufferedImage;
import java.util.concurrent.RecursiveAction;

public class ForkBlur extends RecursiveAction {
    private Colors colors;
    private final BufferedImage inImage;
    private final BufferedImage outImage;
    private final int xStart;
    private final int xEnd;
    private final int yStart;
    private final int yEnd;
    private final int blurWidth = 11;
    public ForkBlur(Colors colors, BufferedImage inImage, BufferedImage outImage, int xStart, int xEnd, int yStart, int yEnd) {
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

        int yDelta = (yEnd-yStart)+1;
        if(yDelta>(2*blurWidth)){
            int yMid = (yStart+yEnd)>>>1;
            int xMid = (xStart+xEnd)>>>1;
            invokeAll(
                    new ForkBlur(colors,inImage,outImage,xStart,xMid,yStart,yMid),
                    new ForkBlur(colors,inImage,outImage,xMid,xEnd,yStart,yMid),
                    new ForkBlur(colors,inImage,outImage,xStart,xMid,yMid,yEnd),
                    new ForkBlur(colors,inImage,outImage,xMid,xEnd,yMid,yEnd)
            );
        }else {
            for (int x = xStart; x < xEnd; x++) {
                for (int y = yStart; y < yEnd; y++) {
                    int outPixel = colors.getBlurredPixel(inImage, x, y, blurWidth);
                    outImage.setRGB(x, y, outPixel);
                }
            }
        }
    }
}
