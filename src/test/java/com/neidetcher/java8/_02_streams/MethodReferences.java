package com.neidetcher.java8._02_streams;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MethodReferences {

    List<String> groceryList = Arrays.asList(
            "spinach",
            "ribs",
            "bacon",
            "brussels sprouts",
            "eggs");

    List<Integer> expectedDoubleLengths = Arrays.asList(14, 8, 10, 32, 8);

    private int doubleIt(int arg){
        return arg * 2;
    }

    @Test public void noMethodReference(){
        List<Integer> lengths = groceryList
                .stream()
                .map(curr -> curr.length())
                .map(curr -> doubleIt(curr))
                .collect(Collectors.toList());

        assertEquals(expectedDoubleLengths, lengths);
    }

    @Test public void methodReference(){
        List<Integer> lengths = groceryList
                .stream()
                .map(String::length)
                .map(this::doubleIt)
                .collect(Collectors.toList());

        assertEquals(expectedDoubleLengths, lengths);
    }

    /////////////////////////////////////////////////////////////////
    // The 3 types of Method References

    @Test public void staticMethodReferenceOnType(){
        int result = IntStream.range(0, 10)
                .reduce(0, Integer::sum); // .reduce(0, (a, b) -> a + b);

        assertEquals(45, result);
    }

    @Test public void instanceMethodReferenceOnType(){
        List<Integer> result = groceryList.stream()
                .map(String::length) // .map(curr -> curr.length())
                .collect(Collectors.toList());
    }

    class Tripler{
        int tripleIt(int arg){
            return arg * 3;
        }
    }

    @Test public void instanceMethodOnInstance(){
        Tripler tripler = new Tripler();

        int result = IntStream.range(0, 10)
                .map(tripler::tripleIt)// .map(curr -> tripler.tripleIt(curr))
                .sum();

        assertEquals(135, result);
    }
}
