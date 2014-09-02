package com.neidetcher.java8._99_sandbox;

import java.util.stream.IntStream;

public class FizzBuzz {
    public static void main(String[] args){
        IntStream.range(1, 100)
                .boxed()
                .map(x -> x+": " + (x%3==0? "Fizz": "") + (x%5==0? "Buzz": ""))
                .forEach(System.out::println);
    }
}
