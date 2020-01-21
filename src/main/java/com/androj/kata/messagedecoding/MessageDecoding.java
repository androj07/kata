package com.androj.kata.messagedecoding;

/**
 * This problem was asked by Facebook.
 * <p>
 * Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.
 * <p>
 * For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.
 * <p>
 * You can assume that the messages are decodable. For example, '001' is not allowed.
 */
public class MessageDecoding {


    public int possibleDecodings(String message) {
        if (message.length() < 2) {
            return 1;
        }
        int totalDecodable = 0, decodableSoFar = 1, previousTotal = 1;
        char[] singleLetters = message.toCharArray();
        for (int i = 0; i < message.length() - 1; i++) {
            int firstInDouble = singleLetters[i];
            int secondInDouble = singleLetters[i + 1];
            totalDecodable = previousTotal;
            if ((firstInDouble == '1' && secondInDouble <= '9') || (firstInDouble == '2' && secondInDouble <= '6')) {
                totalDecodable += decodableSoFar;
            }
            decodableSoFar = previousTotal;
            previousTotal = totalDecodable;
        }

        return totalDecodable;

    }

}
