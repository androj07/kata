package com.androj.kata.pairforsum;

import java.util.HashSet;
import java.util.Set;

public class PairsForSum {

    public boolean containsAPairGivingSum(int[] numbers, int sum) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }

        Set<Integer> visitedNumbers = new HashSet<>();
        for (int number : numbers) {
            Integer searchComplement = sum - number;
            if (visitedNumbers.contains(searchComplement)) {
                return true;
            }
            visitedNumbers.add(number);
        }
        return false;
    }
}
