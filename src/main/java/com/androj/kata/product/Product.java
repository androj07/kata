package com.androj.kata.product;

public class Product {

    public int[] productAllExceptCurrentElement(int[] in) {
        int[] out = new int[in.length];

        int left = in[0];
        int right = in[in.length - 1];
        for (int i = 1, j = in.length - 2; i < in.length && j >= 0; i++, j--) {
            if (i <= j || i == out.length - 1) {
                out[i] = left;
            } else {
                out[i] = out[i] * left;

            }
            if (j > i || j == 0) {
                out[j] = right;
            } else {
                out[j] = out[j] * right;
            }

            left = left * in[i];
            right = right * in[j];
        }

        return out;
    }
}
