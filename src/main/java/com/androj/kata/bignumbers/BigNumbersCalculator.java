package com.androj.kata.bignumbers;

public class BigNumbersCalculator {
    private final static byte BASE = 10;

    public byte[] add(byte[] first, byte[] second) {
        int digitsToAdd = Math.max(first.length, second.length);

        byte[] sum = new byte[digitsToAdd + 1];
        byte c = 0;

        for (int i = 0; i < digitsToAdd; i++) {
            byte a = getDigitOnPositionIfExists(first, i);
            byte b = getDigitOnPositionIfExists(second, i);
            int tmp = a + b + c;
            if (tmp >= BASE) {
                c = 1;
                tmp = tmp - BASE;
            } else {
                c = 0;
            }
            sum[sum.length-1-i] = (byte) tmp;
        }
        if(c!=0){
            sum[0] = c;
        }

        return sum;
    }

    private byte getDigitOnPositionIfExists(byte[] digits, int i) {
        byte digit = 0;
        int index = (digits.length - 1) - i;
        if (index >= 0) {
            digit = digits[index];
        }
        return digit;
    }
}
