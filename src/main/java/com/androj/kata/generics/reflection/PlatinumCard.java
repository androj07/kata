package com.androj.kata.generics.reflection;

import java.nio.ByteBuffer;

public class PlatinumCard implements IPersistable {
    public String platinumSpecific = "PLATINUM";

    @Override
    public void fromBuffer(ByteBuffer input) {

    }

    @Override
    public ByteBuffer toBuffer() {
        return null;
    }
}
