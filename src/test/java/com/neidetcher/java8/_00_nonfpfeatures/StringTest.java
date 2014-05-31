package com.neidetcher.java8._00_nonfpfeatures;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class StringTest {

    List<String> groceryList = Arrays.asList(
            "spinach",
            "ribs",
            "bacon",
            "brussels sprouts",
            "eggs");
    String del = ", ";

    String expectedShoppingListString =
            "spinach, ribs, bacon, brussels sprouts, eggs";

    @Test public void preJava8(){
        StringBuilder sb = new StringBuilder();

        for(String item : groceryList) {
            sb.append(item + del);
        }

        assertEquals(
                expectedShoppingListString,
                sb.toString().substring(0, sb.length() - del.length()));
    }

    @Test public void stringJoiner() {
        StringJoiner sj = new StringJoiner(del);

        for(String item : groceryList){
            sj.add(item);
        }

        assertEquals(expectedShoppingListString, sj.toString());
    }

    @Test public void stringJoinerForEach() {
        StringJoiner sj = new StringJoiner(del);

        groceryList.forEach(sj::add);

        assertEquals(expectedShoppingListString, sj.toString());
    }

    @Test public void string_join() {
        assertEquals(
                expectedShoppingListString,
                String.join(del, groceryList));
    }
}
