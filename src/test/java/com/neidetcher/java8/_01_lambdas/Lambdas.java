package com.neidetcher.java8._01_lambdas;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.function.Function;
import java.util.function.Predicate;

public class Lambdas {

    @Test public void lambdasOnTheirOwn(){
        Runnable r = () -> System.out.println("hello world");
        r.run();

        Predicate<String> isEmpty = s -> s.isEmpty();
        assertFalse(isEmpty.test("FOO"));
    }

    @Test public void lambdasFromFunctionalInterfaces0(){
        Function<Long, Long> f = new Function<Long, Long>(){
            @Override public Long apply(Long arg){
                return arg * 2;
            }
        };

        Long result = f.apply(5L);
        assertTrue(10 == result);
    }

    @Test public void lambdasFromFunctionalInterfaces1(){
        Function<Long, Long> f = (Long arg) -> {
            return arg * 2;
        };

        Long result = f.apply(5L);
        assertTrue(10 == result);
    }

    @Test public void lambdasFromFunctionalInterfaces2(){
        Function<Long, Long> f = num -> num * 2;

        Long result = f.apply(5L);
        assertTrue(10 == result);
    }
}
