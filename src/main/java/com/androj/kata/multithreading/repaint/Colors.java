package com.androj.kata.multithreading.repaint;

import java.awt.image.BufferedImage;

public class Colors {
    public int makePurpleIfShadeOfGray(int pixel) {
        if (isShadeOfGray(pixel)) {
            int red = getRed(pixel);
            int green = Math.max(getGreen(pixel) - 80, 0);
            int blue = getBlue(pixel);
            return createPixelFromColors(red, green, blue);
        }
        return pixel;
    }

    private boolean isShadeOfGray(int pixel) {
        int margin = 35;
        return (Math.abs(getRed(pixel) - getGreen(pixel)) < margin && Math.abs(getGreen(pixel) - getBlue(pixel)) < margin && Math.abs(getBlue(pixel) - getRed(pixel)) < margin);
    }


    private int getRed(int pixel) {
        return (pixel & 0x00FF0000) >> 16;
    }

    private int getGreen(int pixel) {
        return (pixel & 0x0000FF00) >> 8;
    }


    private int getBlue(int pixel) {
        return (pixel & 0x000000FF);
    }

    private int createPixelFromColors(int red, int green, int blue) {
        int pixel = 0;

        pixel |= blue;
        pixel |= green << 8;
        pixel |= red << 16;

        pixel |= 0xFF000000;

        return pixel;
    }

    public int getBlurredPixel(BufferedImage inImage, int x, int y,int blurWidth) {
        float samples = blurWidth*blurWidth;
        int sidePixels = (blurWidth - 1) / 2;
        float rt = 0, gt = 0, bt = 0;
        for (int xi = -sidePixels; xi <= sidePixels; xi++) {
            int xIndex = Math.min(Math.max(xi + x, 0),
                    inImage.getWidth() - 1);

            for (int yi = -sidePixels; yi <= sidePixels; yi++) {
                int yIndex = Math.min(Math.max(yi + y, 0),
                        inImage.getHeight() - 1);

                int pixel = inImage.getRGB(xIndex, yIndex);
                rt += getRed(pixel) / samples;
                gt += getGreen(pixel) / samples;
                bt += getBlue(pixel) / samples;
            }
        }
        //System.out.println(rt+" "+gt+" "+bt);
        return createPixelFromColors((int)rt, (int)gt, (int)bt);
    }

}
