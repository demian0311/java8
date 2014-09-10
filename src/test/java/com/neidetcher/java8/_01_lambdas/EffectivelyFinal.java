package com.neidetcher.java8._01_lambdas;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class EffectivelyFinal {

    List<String> groceryList = Arrays.asList(
            "spinach",
            "ribs",
            "bacon",
            "brussels sprouts",
            "eggs");

    /**
     * In a loop you're used to mutating variables in the surrounding scope.
     *
     * Yeah, you should be doing currFood.contains()
     */
    @Test public void looping(){
        boolean hasBestMeat = false;
        String bestMeat = "ribs";

        for(String currFood: groceryList){
            if(currFood.equals(bestMeat)){
                hasBestMeat = true;
            }
        }

        assertTrue(hasBestMeat);
    }

    /* Won't compile.  You can't mutate variables within the lambda.
    @Test public void cant_mutate_variable(){
        boolean hasBestMeat = false;
        String bestMeat = "ribs";

        groceryList.stream().forEach(curr -> {if(curr.equals(bestMeat){hasBestMeat = true;}});
    }

    /** Won't compile.  Even if the variable is changed _after_ the lambda you can't access it
     * from within the lambda.
     * */
    @Test public void not_effectively_final(){
        String bestMeat = "ribs";

        boolean hasBestMeat = groceryList.stream()
                .anyMatch(curr -> curr.equals(bestMeat));

        if(false){
            bestMeat = "bacon";
        }

        assertTrue(hasBestMeat);
    }

    /**
     * This is allowed, bestMeat is effectively final.  It doesn't
     * have to be declared as final.
     */
    @Test public void testLambda(){
        String bestMeat = "ribs";

        boolean hasBestMeat = groceryList.stream()
                .anyMatch(curr -> curr.equals(bestMeat));

        assertTrue(hasBestMeat);
    }
}
