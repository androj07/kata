package com.androj.kata.missingvalue;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MissingValuesTest {
    @Test
    public void testMissingSingleValue() {

        int[] input = {9, 0, 10, 5, 4, 3, 2, 1, 8, 6};

        int missing = MissingValues.findMissingValueInArray(input);

        assertThat(missing, equalTo(7));

    }

    @Test
    public void testMissingMultipleValues() {

        int[] input = {0, 10, 5, 4, 3, 2, 1, 8, 6};

        int[] missing = MissingValues.findMissingValuesInArray(input, 10);

        int[] expected = {7, 9};
        assertTrue(Arrays.equals(missing, expected));

    }

    @Test
    public void testMissingValueAnia() {

        int[] input = {9, 1, 10, 5, 4, 3, 2, 7, 8, 6};

        int missing = MissingValues.findMissingValueWithFormula(input);

        int expected = 0;
        assertThat(missing, equalTo(expected));
    }
}