package com.androj.kata.generics.reflection;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class ReaderTest {

    @Test
    public void shouldReadPlatinumCards(){

        Reader<PlatinumCard> reader = new Reader<>(PlatinumCard.class);

        PlatinumCard next = reader.next();
        assertThat(next.platinumSpecific,equalTo("PLATINUM"));
    }

    @Test
    public void shouldReadGoldCards(){

        Reader<GoldCard> reader = new Reader<>(GoldCard.class);

        GoldCard next = reader.next();
        assertThat(next.goldSpecific,equalTo("GOLD"));
    }
}