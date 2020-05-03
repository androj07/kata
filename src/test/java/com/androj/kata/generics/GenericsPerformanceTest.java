package com.androj.kata.generics;

import com.androj.kata.generics.factory.IReader;
import com.androj.kata.generics.factory.ReaderFactory;
import com.androj.kata.generics.reflection.PlatinumCard;
import com.androj.kata.generics.reflection.Reader;
import org.junit.Test;

public class GenericsPerformanceTest {

    @Test
    public void create10mlnObjectEachMethod() {
        double factoryAverage = 0.0;
        double reflectionAverage = 0.0;
        int repeats = 1000;
        for (int j = 0; j < repeats; j++) {


            IReader<PlatinumCard> readerForClass = ReaderFactory.getReaderForClass(PlatinumCard.class);

            long start = System.currentTimeMillis();
            for (int i = 0; i < 10_000_000; i++) {
                PlatinumCard next = readerForClass.next();
            }
            long end = System.currentTimeMillis();


            Reader<PlatinumCard> reader = new Reader<>(PlatinumCard.class);
            long start1 = System.currentTimeMillis();
            for (int i = 0; i < 10_000_000; i++) {
                PlatinumCard next = reader.next();
            }
            long end1 = System.currentTimeMillis();

            long factory = end - start;
            long reflection = end1 - start1;

            factoryAverage =+ ((double)factory/repeats);
            reflectionAverage =+ ((double)reflection/repeats);

        }

        System.out.printf("Factory method took %f\n", factoryAverage);
        System.out.printf("Reflection method took %f\n", reflectionAverage);

        System.out.println(((factoryAverage / (double)reflectionAverage) - 1));
        System.out.println(((reflectionAverage / (double)factoryAverage) - 1));

    }

  }
