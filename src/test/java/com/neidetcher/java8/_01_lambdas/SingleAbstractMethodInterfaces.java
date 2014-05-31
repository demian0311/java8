package com.neidetcher.java8._01_lambdas;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.*;

import static org.junit.Assert.*;

public class SingleAbstractMethodInterfaces {

    @FunctionalInterface interface StringManipulator{
        String doIt(String arg);
        //String doThat(String arg);
    }

    @Test public void stringManipulator(){
        StringManipulator sm = (s) -> s.toUpperCase();
        assertEquals("HELLO", sm.doIt("Hello"));
    }

    @Test public void runnable(){
        Runnable r = () -> System.out.println("Foo");
        r.run();
    }

    @Test public void comparator(){
        Comparator<String> c = (a, b) -> a.length() - b.length();
        assertEquals(0, c.compare("AAA", "BBB"));
    }

    @Test public void function(){
        Function<String, Integer> getLength = (s) -> s.length();
        assertTrue(3 == getLength.apply("AAA"));
    }

    @Test public void predicate(){
        Predicate<String> isEmpty = (s) -> s.isEmpty();
        assertTrue(isEmpty.test(""));
    }

    @Test public void consumer(){
        Consumer<String> say = (s) -> System.out.println(s);
        say.accept("Hello World");
    }

    @Test public void supplier(){
        Supplier<String> currTime = () -> ""+System.currentTimeMillis();
        System.out.println(currTime.get());
    }

    @Test public void primitiveFunctionalInterfaces(){
        IntPredicate isEven = (ii) -> ii % 2 == 0;

        assertTrue(isEven.test(20));
        assertFalse(isEven.test(11));
    }

    @Test public void toIntFunction(){
        ToIntFunction<String> length = s -> s.length();
        assertTrue(3 == length.applyAsInt("foo"));
    }

    @Test public void unaryOperator(){
        UnaryOperator<Long> abs = (ii) -> (ii < 1)? -1 * ii: ii;

        assertEquals(Long.valueOf(20), abs.apply(-20l));
        assertEquals(Long.valueOf(10), abs.apply(10l));
    }

    @Test public void biConsumer(){
        BiConsumer<String, Integer> sayTimes = (ss, ii) -> {
            for(int curr = 0; curr < ii; curr++){
                System.out.println(ss);
            }
        };

        sayTimes.accept("foo", 5);
    }
}
