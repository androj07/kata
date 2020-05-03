package com.androj.kata.generics.reflection;

import java.lang.reflect.Constructor;

public class Reader<T extends IPersistable> {

    private Class<T> forClass;
    private Constructor<T> noParamsConstructor;

    public Reader(Class<T> forClass) {
        try {
            this.noParamsConstructor = forClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("The requested class needs to have no params constructor");
        }
    }

    public <T extends IPersistable> T next() {
        try {
            IPersistable persistable =  noParamsConstructor.newInstance();
            persistable.fromBuffer(null);
            return (T) persistable;
        } catch (Exception e) {
            return null;
        }
    }


}
