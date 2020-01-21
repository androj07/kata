package com.androj.kata.product;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ProductTest {

    private final Product product = new Product();

    @Test
    public void nestor() {
        int[] input1 = {3, 2, 1};
        int[] expected1 = {2, 3, 6};


        int[] out1 = product.productAllExceptCurrentElement(input1);
        assertThat(out1,equalTo(expected1));
        System.out.println(Arrays.toString(out1));

        int[] input2 = {1, 2, 3, 4, 5};
        int[] expected2 = {120, 60, 40, 30, 24};

        int[] out2 = product.productAllExceptCurrentElement(input2);
        assertThat(out2,equalTo(expected2));
        System.out.println(Arrays.toString(out2));
    }

}