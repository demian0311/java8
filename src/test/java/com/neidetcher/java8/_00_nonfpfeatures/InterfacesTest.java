package com.neidetcher.java8._00_nonfpfeatures;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class InterfacesTest {
    List<String> groceryList = Arrays.asList(
            "spinach",
            "ribs",
            "bacon",
            "brussels sprouts",
            "eggs");

    String del = ", ";

    String expectedShoppingListString =
            "spinach, ribs, bacon, brussels sprouts, eggs";

    @Test public void preJava8_iterable_looping(){
        StringJoiner sj = new StringJoiner(del);

        for(String item : groceryList){
            sj.add(item);
        }

        assertEquals(expectedShoppingListString, sj.toString());
    }

    @Test public void java8_iterable_forEach() {
        StringJoiner sj = new StringJoiner(del);

        groceryList.forEach(sj::add);

        assertEquals(expectedShoppingListString, sj.toString());
    }

    interface Cowboy{
        public default String draw(){
            return "bang";
        }
    }

    class Caballero implements Cowboy{
        //public String draw(){ return "explosión"; }
    }

    @Test public void defaultMethods(){
        Caballero caballero = new Caballero();
        assertEquals("bang", caballero.draw());
    }

    interface Circle{
        public default String draw(){
            return "o";
        }
    }

    class CircleCowboy implements Circle, Cowboy{

        // Required to disambiguate what method to use.
        public String draw(){
            return Cowboy.super.draw();
            //return Circle.super.draw();
        }
    }

    @Test public void mustDisambiguate(){
        CircleCowboy circleCowboy = new CircleCowboy();
        assertEquals("bang", circleCowboy.draw());
    }

    abstract class DeckOfCards{
        public String draw(){
            return "A♠";
        }
    }

    class CircleDeckOfCards extends DeckOfCards implements Circle{
    }

    @Test public void classesWin(){
        CircleDeckOfCards circleDeckOfCards = new CircleDeckOfCards();
        assertEquals("A♠", circleDeckOfCards.draw());
    }
}
