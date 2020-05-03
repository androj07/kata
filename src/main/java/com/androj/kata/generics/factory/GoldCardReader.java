package com.androj.kata.generics.factory;

import com.androj.kata.generics.reflection.GoldCard;

public class GoldCardReader extends PersistableReader<GoldCard> implements IReader<GoldCard> {

    @Override
    public GoldCard next() {
        GoldCard goldCard = new GoldCard();
        this.fillWithData(goldCard);
        return goldCard;
    }
}
