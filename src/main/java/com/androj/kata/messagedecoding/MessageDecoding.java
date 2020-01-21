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

    private final static int WINDOW_SIZE = 2;
    public int possibleDecodings(String message) {
        //You can assume that the messages are decodable. For example, '001' is not allowed.
        if (message.isEmpty()) {
            return 0;
        }

        if (message.length() == 1) {
            return 1;
        }

        //count doubles
        int decodablePairs = 0;
        int undecodablePairs = 0;
        char[] singleLetters = message.toCharArray();
        for (int i = 0; i < message.length() - 1; i++) {
            int firstInDouble = singleLetters[i];
            int secondInDouble = singleLetters[i + 1];
            if ((firstInDouble == '1' && secondInDouble <= '9') || (firstInDouble == '2' && secondInDouble <= '6')) {
                decodablePairs++;
            }else{
                undecodablePairs++;
            }
        }

        if (decodablePairs == 0) {
            //single chars will be always decodable so 1
            return 1;
        }

        if(message.length()<4){
            return 1 + decodablePairs;
        }

        return 1 + (decodablePairs) + (message.length() - WINDOW_SIZE - undecodablePairs);
    }

}
