package com.androj.kata.missingvalue;

import java.util.BitSet;

public class MissingValues {

    public static int findMissingValueInArray(int[] values) {

        BitSet bitSet = new BitSet(values.length + 1);
        for (int value : values) {
            bitSet.set(value);
        }

        return bitSet.nextClearBit(0);
    }


    public static int findMissingValueWithFormula(int[] values) {
            int artSum = ((values.length) * (values.length+1))/2;

            int sum = 0;
        for (int value : values) {
            sum += value;
        }

        return artSum-sum;
    }



    public static int[] findMissingValuesInArray(int[] values, int maxValue) {

        BitSet bitSet = new BitSet(maxValue);
        for (int value : values) {
            bitSet.set(value);
        }

        int currentBit = 0;
        int[] result = {};
        while (currentBit < maxValue) {
            int emptyBit = bitSet.nextClearBit(currentBit);
            int tmp[] = new int[result.length + 1];
            if(result.length>0) {
                System.arraycopy(result, 0, tmp, 0, tmp.length-1);
            }
            tmp[tmp.length - 1] = emptyBit;
            result = tmp;
            currentBit = emptyBit+1;
        }

        return result;
    }
}
