package com.androj.kata.generics.factory;

import com.androj.kata.generics.reflection.GoldCard;
import com.androj.kata.generics.reflection.PlatinumCard;

public class ReaderFactory {

    public static IReader getReaderForClass(Class persistable) {
        if (persistable.getCanonicalName().equals(GoldCard.class.getCanonicalName())) {
            return new GoldCardReader();
        } else if (persistable.getCanonicalName().equals(PlatinumCard.class.getCanonicalName())) {
            return new PlatinumCardReader();
        }
        throw new IllegalStateException("Unsupported class");
    }

}
