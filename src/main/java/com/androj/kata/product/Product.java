package com.androj.kata.product;

/**
 * This problem was asked by Uber.
 *
 * Given an array of integers, return a new array such that each element at index i
 * of the new array is the product of all the numbers in the original array except the one at i.
 *
 * For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24].
 * If our input was [3, 2, 1], the expected output would be [2, 3, 6]. Don't use division and find a solution with time complexity O(n).
 */
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
