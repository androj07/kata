package com.androj.kata.generics.reflection;

import java.nio.ByteBuffer;

public interface IPersistable {
    void fromBuffer(ByteBuffer input);
    ByteBuffer toBuffer();
}
