package com.androj.kata.generics.reflection;

import java.nio.ByteBuffer;

public class GoldCard implements IPersistable{
    public String goldSpecific = "GOLD";

    @Override
    public void fromBuffer(ByteBuffer input) {

    }

    @Override
    public ByteBuffer toBuffer() {
        return null;
    }
}
