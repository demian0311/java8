package com.neidetcher.java8._01_lambdas;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class LambdasWithStreams {

    List<String> groceryList = Arrays.asList(
            "spinach",
            "ribs",
            "bacon",
            "brussels sprouts",
            "eggs");

    @Test public void withStreams0(){
        Predicate<String> isMeat = new Predicate<String>() {
            @Override public boolean test(String s) {
                return (s.equals("ribs") || s.equals("bacon") || s.equals("steak"));
            }
        };

        UnaryOperator<String> upperCase = new UnaryOperator<String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };

        List<String> loudMeat = groceryList.stream()
                .filter(s -> isMeat.test(s))
                .map(s -> upperCase.apply(s))
                .collect(Collectors.toList());

        assertEquals(Arrays.asList("RIBS", "BACON"), loudMeat);
    }

    @Test public void withStreams1(){
        Predicate<String> isMeat =  (String s) ->
                (s.equals("ribs") || s.equals("bacon") || s.equals("steak"));

        UnaryOperator<String> upperCase = (String s) -> s.toUpperCase();

        List<String> loudMeat = groceryList.stream()
                .filter(s -> isMeat.test(s))
                .map(s -> upperCase.apply(s))
                .collect(Collectors.toList());

        assertEquals(Arrays.asList("RIBS", "BACON"), loudMeat);
    }

    @Test public void withStreams2(){
        List<String> loudMeat = groceryList.stream()
                .filter(s -> s.equals("ribs") || s.equals("bacon") || s.equals("steak"))
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());

        assertEquals(Arrays.asList("RIBS", "BACON"), loudMeat);
    }
}
