package com.androj.kata.generics.factory;

import com.androj.kata.generics.reflection.IPersistable;

public abstract class PersistableReader<T extends IPersistable> {

    public T fillWithData(T dataHolder){
        //simulates read from buffer
        dataHolder.fromBuffer(null);
        return dataHolder;
    }
}
