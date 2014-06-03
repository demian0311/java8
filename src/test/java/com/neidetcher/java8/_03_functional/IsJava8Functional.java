package com.neidetcher.java8._03_functional;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;

public class IsJava8Functional {

    public int factorial(int i) {
        return (i == 0) ? 1 : i * factorial(i - 1);
    }

    @Test public void recursion(){
        assertEquals(120, factorial(5));
    }

    public int timesTwo(int i){
        return i * 2;
    }

    @Test public void pureFunctions() {
        assertEquals(10, timesTwo(5));
    }

    public Integer timer(Function<Integer, Integer> f, Integer arg){
        Long start = System.currentTimeMillis();
        Integer valueToReturn = f.apply(arg);
        System.out.println("took: " + (System.currentTimeMillis() - start) + "ms");
        return valueToReturn;
    }

    @Test public void functionsThatTakeOtherFunctions() {
        assertEquals(Integer.valueOf(15), timer(x -> x * 3, 5));
    }

    public Function<Integer, Integer> multiplyBy(Integer arg) {
        return (x -> x * arg);
    }

    @Test public void functionsThatCreateOtherFunctions() {
        assertEquals(Integer.valueOf(15), multiplyBy(3).apply(5));
    }
}

