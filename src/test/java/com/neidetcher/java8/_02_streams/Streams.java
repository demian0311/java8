package com.neidetcher.java8._02_streams;

import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

public class Streams {

    @Test public void loopCount(){
        for(int ii = 0; ii < 10; ii++){
            System.out.println(ii);
        }
    }

    @Test public void streamCount(){
        IntStream.range(0, 10).forEach(System.out::println);
    }

    @Test public void randomStream(){
        new Random()
                .ints(0, 10)
                .limit(10)
                .forEach(System.out::println);
    }
}
