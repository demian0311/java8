package com.neidetcher.java8._02_streams;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
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

    List<String> groceryList = Arrays.asList(
            "spinach",
            "ribs",
            "bacon",
            "brussels sprouts",
            "eggs");


    @Test public void streamPipeline() {
        List<Integer> sortedMeatListStringSize = groceryList.stream()
                .filter(curr -> (curr.equals("ribs") || curr.equals("bacon") || curr.equals("steak")))
                .map(String::length)
                .sorted()
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(4, 5), sortedMeatListStringSize);
    }
}
