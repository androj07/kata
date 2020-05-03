package com.androj.kata.generics.factory;

import com.androj.kata.generics.reflection.GoldCard;
import com.androj.kata.generics.reflection.PlatinumCard;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ReaderFactoryTest {
    @Test
    public void shouldReadPlatinumCards() {
        IReader<PlatinumCard> readerForClass = ReaderFactory.getReaderForClass(PlatinumCard.class);

        PlatinumCard next = readerForClass.next();
        assertThat(next.platinumSpecific, equalTo("PLATINUM"));
    }

    @Test
    public void shouldReadGoldCards() {
        IReader<GoldCard> readerForClass = ReaderFactory.getReaderForClass(GoldCard.class);

        GoldCard next = readerForClass.next();
        assertThat(next.goldSpecific, equalTo("GOLD"));
    }
}