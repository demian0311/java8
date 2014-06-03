package com.neidetcher.java8._02_streams;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    @Test public void source(){
        Stream<String> groceryListStream = groceryList.stream();
        assertEquals(5, groceryListStream.count());
    }

    @Test public void filter(){
        List<String> bGroceryList = groceryList
                .stream()                             // source
                .filter(curr -> curr.startsWith("b")) // intermediate
                .collect(Collectors.toList());        // terminal

        assertEquals(Arrays.asList("bacon", "brussels sprouts"), bGroceryList);
    }

    @Test public void map(){
        List<Integer> bGroceryList = groceryList
                .stream()                             // source
                .filter(curr -> curr.startsWith("b")) // intermediate
                .map(String::length)                  // intermediate
                .collect(Collectors.toList());        // terminal

        assertEquals(Arrays.asList(5, 16), bGroceryList);
    }

    @Test public void sorted(){
        List<String> bGroceryList = groceryList
                .stream()                             // source
                .filter(curr -> curr.startsWith("b")) // intermediate
                .sorted()                             // intermediate
                .collect(Collectors.toList());        // terminal

        assertEquals(Arrays.asList("bacon", "brussels sprouts"), bGroceryList);
    }

    @Test public void forEach(){
        IntStream
                .range(0, 10)                  // source
                .forEach(System.out::println); // terminal
    }

    @Test public void reduce(){
        int evenNumbersSum = IntStream
                .range(0, 10)                  // source
                .filter(curr -> curr % 2 == 0) // intermediate
                .reduce(0, Integer::sum);      // terminal

        assertEquals(20, evenNumbersSum);
    }

    @Test public void collect(){
        List<String> bGroceryList = groceryList
                .stream()                             // source
                .filter(curr -> curr.startsWith("b")) // intermediate
                .sorted()                             // intermediate
                .collect(Collectors.toList());        // terminal

        assertEquals(Arrays.asList("bacon", "brussels sprouts"), bGroceryList);
    }

    @Test public void collectMultipleArgument(){
        List<String> bGroceryList = groceryList
                .stream()                             // source
                .filter(curr -> curr.startsWith("b")) // intermediate
                .sorted()                             // intermediate
                .collect(                             // terminal
                        ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll);

        assertEquals(Arrays.asList("bacon", "brussels sprouts"), bGroceryList);
    }

    String categorize(String foodIn) {
        switch(foodIn){
            case "bacon":   return "meat";
            case "ribs":    return "meat";
            case "steak":   return "meat";
            case "chicken": return "meat";
        }

        return "notMeat";
    }

    @Test public void collectGroupBy(){
        Map<String, List<String>> expected = new HashMap<String, List<String>>(){{
            put("notMeat", Arrays.asList("spinach", "brussels sprouts", "eggs"));
            put("meat", Arrays.asList("ribs", "bacon"));
        }};

        Map<String, List<String>> foo = groceryList
                .stream()
                .collect(Collectors.groupingBy(this::categorize));

        assertEquals(expected, foo);
    }
}

