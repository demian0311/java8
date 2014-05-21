package com.neidetcher.java8._00_nonfpfeatures;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class StringTest {
    // TODO: show how bad it was to do without the joiners

    @Test public void stringJoin() {
        List<String> stringList = Arrays.asList("this", "that", "theOther");

        String allTogether = String.join(", ", stringList);
        System.out.println("allTogether: " + allTogether);

        assertEquals("this, that, theOther", allTogether);
    }

    @Test public void stringJoiner() {
        StringJoiner sj = new StringJoiner(", ");
        sj.add("this");
        sj.add("that");
        sj.add("theOther");

        assertEquals("this, that, theOther", sj.toString());
    }

}
