package com.androj.kata.messagedecoding;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class MessageDecodingTest {

    private final MessageDecoding decoding = new MessageDecoding();

    @Test
    public void decodingTests(){
        assertThat(decoding.possibleDecodings(""),equalTo(1));
        assertThat(decoding.possibleDecodings("1"),equalTo(1));
        assertThat(decoding.possibleDecodings("3131"),equalTo(2));
        assertThat(decoding.possibleDecodings("111"),equalTo(3));
        assertThat(decoding.possibleDecodings("117"),equalTo(3));
        assertThat(decoding.possibleDecodings("999"),equalTo(1));
        assertThat(decoding.possibleDecodings("176"),equalTo(2));
        assertThat(decoding.possibleDecodings("1311"),equalTo(4));
        assertThat(decoding.possibleDecodings("1111"),equalTo(5));
    }
}