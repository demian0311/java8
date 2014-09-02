package com.neidetcher.java8._99_sandbox;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.*;

/**
 * Going through the new Manning book.
 */
public class Java8InAction {

    @Test
    public void usingMap() {

        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println("wordLengths: " + wordLengths);
    }

    @Test public void flattening(){
        // we want to find distinct letters in a bunch of words
        List<String> words = Arrays.asList("Hello", "World");

        // not what we wanted
        List<String[]> notReallyLetters = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());

        System.out.println("notReallyLetters: " + notReallyLetters);

        List<String> letters = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream) // flatten each stream into a single stream
                .distinct()
                .collect(Collectors.toList());

        System.out.println("letters: " + letters);
    }

    private List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

    @Test public void quiz5_2(){
        List<Integer> squares = numbers.stream()
                .map(number -> number * number)
                .collect(Collectors.toList());

        System.out.println("squares: " + squares);
    }

    // TODO: this shows getting a stream from an Array
    // as well as Optional for primitives
    @Test public void optionalPrimitiveWrappers() {
        int[] numArr = {1, 2, 3, 4, 5};

        OptionalInt maxOption = Arrays.stream(numArr).max();
        System.out.println("maxOption: " + maxOption);

        assertTrue(maxOption.isPresent());
        assertEquals(5, maxOption.getAsInt());
    }

    // TODO: include this in the streams section?
    // maybe a gentler introduction without talking about how you
    // can get them out of collections?
    @Test public void stream_of() {
        Stream<String> stream = Stream.of("dogs", "cats", "tigers", "wolves");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }

    // TODO: get this working
    @Test public void dealing_with_exceptions(){
        String path = "~/.bashrc";
        try{
            Stream<String> lines = Files.lines(Paths.get(path));
            System.out.println("line count: " + lines.count());
        } catch (IOException e){
            System.out.println("IOException: " + e);
        }
    }

    @Test public void stream_iterate_fibonacci(){
        //             / what you start with
        //             |                / lambda with the array as an argument
        //             |                |    / creates an array of 2 elements
        //             |                |    |         / 0th element is from the old 1th element
        //             |                |    |         |     / 1th element is old 0th element + 1th element
        //             |                |    |         |     |
        Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
                .limit(10)                     // just do 10 of them
                .map(arr -> arr[0])            // pull the first one out of our working array
                .forEach(System.out::println); // print it
    }

    @Test public void stream_generate(){
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
    }

    // mutable state, bad
    @Test public void stream_generate_fibonacci(){
        IntSupplier fib = new IntSupplier(){
            private int previous = 0;
            private int current = 1;

            public int getAsInt(){
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };

        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }

    // showing collectors
    class Story{
        public final String id, engineer;
        public final Integer points;

        Story(String idIn, String engineerIn, Integer pointsIn){
            id = idIn;
            engineer = engineerIn;
            points = pointsIn;
        }

        public String toString(){
            return String.format("Story(%s, %s, %s)", id, engineer, points);
        }
    }

    List<Story> storiesForSprint = Arrays.asList(
            new Story("CST-1", "Brandon", 3),
            new Story("CST-2", "Brandon", 1),
            new Story("CST-3", "Demian", 5),
            new Story("CST-4", "Demian", 13)
    );

    @Test public void find_out_who_to_lay_off(){
        Map<String, Integer> pointsForEngineer = storiesForSprint.stream()
                .collect(Collectors.groupingBy(
                        s -> s.engineer,
                        Collectors.summingInt(s -> s.points)));

        System.out.println("pointsForEngineer: " + pointsForEngineer);
        // pointsForEngineer: {Demian=18, Brandon=4}
    }

    @Test public void parallel() {
        long start1 = System.currentTimeMillis();
        Long sum1 = Stream.iterate(1L, i -> i + 1)
                .limit(4000)
                .reduce(0L, Long::sum);

        long sum1Time = System.currentTimeMillis() - start1;

        System.out.println("sum1 took " + sum1Time + "ms");
        System.out.println("sum1: " + sum1);

        long start2 = System.currentTimeMillis();
        Long sum2 = Stream.iterate(1L, i -> i + 1)
                .limit(4000)
                .parallel()
                //.sequential() // compiler must use previous results
                .reduce(0L, Long::sum);

        long sum2Time = System.currentTimeMillis() - start2;

        System.out.println("sum2 took " + sum2Time + "ms");
        System.out.println("sum2: " + sum2);

        assertTrue(sum2Time < sum1Time);
    }

    @Test public void logging() {
        Logger log = Logger.getLogger(this.getClass().getName());
        log.log(Level.WARNING, () -> "data center is a smoking hole");
    }

    @Test public void stream_factorial(){
        long factorial5 = LongStream.rangeClosed(1, 5)
                .reduce(1, (long a, long b) -> a * b);

        System.out.println("factorial5: " + factorial5);
    }
}
