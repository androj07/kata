package com.androj.kata.bytes;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class Keys {

    public static long createKeyWithByteBuffer(short leftPart, int rightPart) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putShort((short) 0);
        buffer.putShort(leftPart);
        buffer.putInt(rightPart);
        ((Buffer) buffer).flip();
        return buffer.getLong();
    }

    public static long createKeyLean(short left, int right){
        long key = 0;
        key |= right;
        key |= (long)left << 32;
        return key;
    }
}
