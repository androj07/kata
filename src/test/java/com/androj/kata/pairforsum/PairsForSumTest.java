package com.androj.kata.pairforsum;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PairsForSumTest {
    private final PairsForSum pairsForSum = new PairsForSum();

    @Test
    public void testContainsPairGivingSum() {
        int[] numbers = {1, 2, 3, 4, 4};
        assertFalse(pairsForSum.containsAPairGivingSum(numbers, 10));
        assertTrue(pairsForSum.containsAPairGivingSum(numbers, 8));
        assertTrue(pairsForSum.containsAPairGivingSum(numbers, 7));
        assertTrue(pairsForSum.containsAPairGivingSum(numbers, 6));
        assertTrue(pairsForSum.containsAPairGivingSum(numbers, 5));
        int[] numbers2 = {5, 2, 3, 8, 10, 22, 7, 9};
        assertTrue(pairsForSum.containsAPairGivingSum(numbers2, 14));
    }

}