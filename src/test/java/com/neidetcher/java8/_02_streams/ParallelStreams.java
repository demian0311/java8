package com.neidetcher.java8._02_streams;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ParallelStreams {

    List<String> groceryList = Arrays.asList(
            "spinach",
            "ribs",
            "bacon",
            "brussels sprouts",
            "eggs");

    String categorize(String foodIn) {
        try{
            Thread.sleep(500l);
        }catch(Exception e){
            e.printStackTrace();
        }

        switch(foodIn){
            case "bacon":   return "meat";
            case "ribs":    return "meat";
            case "steak":   return "meat";
            case "chicken": return "meat";
        }
        return "notMeat";
    }

    @Test public void serial(){
        long begin = System.currentTimeMillis();
        long meatCount = groceryList.stream()
                .peek(curr -> System.out.println("serial: " + curr))
                .filter(curr -> categorize(curr).equals("meat"))
                .count();
        long duration = System.currentTimeMillis() - begin;

        assertEquals(2, meatCount);
        System.out.println("serial-duration: " + duration);
        // 2536 2534 2539
    }

    @Test public void parallel(){
        long begin = System.currentTimeMillis();
        long meatCount = groceryList.parallelStream()
                .peek(curr -> System.out.println("parallel: " + curr))
                .filter(curr -> categorize(curr).equals("meat"))
                .count();
        long duration = System.currentTimeMillis() - begin;

        assertEquals(2, meatCount);
        System.out.println("parallel-duration: " + duration);
        // 511 506 505
    }

    @Test public void singleElementArrayTrick(){
        int expected = 4950;

        int[] actual = {0};
        IntStream.range(0, 100)
                .forEach(ii -> actual[0] += ii);
        System.out.println("serial actual: " + actual[0]);
        assertEquals(expected, actual[0]);

        int[] actual2 = {0};
        IntStream.range(0, 100)
                .parallel()
                .forEach(ii -> actual2[0] += ii);
        System.out.println("parallel actual: " + actual2[0]);
        assertNotEquals(expected, actual2[0]);
    }

    // have an example of messing with something not effectively final
}
