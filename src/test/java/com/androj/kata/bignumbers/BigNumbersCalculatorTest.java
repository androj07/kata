package com.androj.kata.bignumbers;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class BigNumbersCalculatorTest {

    BigNumbersCalculator bigNumbersCalculator = new BigNumbersCalculator();

    @Test
    public void shouldAdd2SingleDigitNumbersToSumLessThanBase() {
        byte[] a = {2};
        byte[] b = {2};

        byte[] sum = bigNumbersCalculator.add(a, b);

        String sumAsString = getValueAsString(sum);
        assertThat(sumAsString,equalTo("4"));
    }

    @Test
    public void shouldAdd2SingleDigitNumbersToSumBiggerThanBase() {
        byte[] a = {9};
        byte[] b = {9};

        byte[] sum = bigNumbersCalculator.add(a, b);

        String sumAsString = getValueAsString(sum);
        assertThat(sumAsString,equalTo("18"));
    }

    @Test
    public void shouldAdd2SingleDigitNumbersToSumEqualToBase() {
        byte[] a = {9};
        byte[] b = {1};

        byte[] sum = bigNumbersCalculator.add(a, b);

        String sumAsString = getValueAsString(sum);
        assertThat(sumAsString,equalTo("10"));
    }

    @Test
    public void shouldAdd2NumbersWithDifferentNumberOfDigits() {
        byte[] a = {9,9,9};
        byte[] b = {1};

        byte[] sum = bigNumbersCalculator.add(a, b);

        String sumAsString = getValueAsString(sum);
        assertThat(sumAsString,equalTo("1000"));
    }

    private String getValueAsString(byte[] value) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean trailing0 = true;
        for (byte b : value) {
            if(b==0 && trailing0){
                continue;
            }else {
                trailing0 = false;
            }
            stringBuilder.append(b);
        }
        return stringBuilder.toString();
    }

}