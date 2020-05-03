package com.androj.kata.generics.factory;

import com.androj.kata.generics.reflection.PlatinumCard;

public class PlatinumCardReader extends PersistableReader<PlatinumCard> implements IReader<PlatinumCard>  {

    @Override
    public PlatinumCard next() {
        PlatinumCard platinumCard = new PlatinumCard();
        this.fillWithData(platinumCard);
        return platinumCard;
    }
}
