package com.neidetcher.java8._00_nonfpfeatures;

import org.junit.Test;
import static org.junit.Assert.*;

public class InterfacesTest {

    interface Cowboy{
        public default String draw(){
            return "bang";
        }
    }

    class Caballero implements Cowboy{
        //public String draw(){ return "explosión"; }
    }

    /**
     * Caballero gets the default method from the interface Cowboy.
     */
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

        /** This is required to disambiguate what
         * default method to use. */
        public String draw(){
            return Cowboy.super.draw();
            //return Circle.super.draw();
        }
    }

    /**
     * 2 interfaces, must disambiguate.
     */
    @Test public void disambiguate(){
        CircleCowboy circleCowboy = new CircleCowboy();
        assertEquals("bang", circleCowboy.draw());
    }

    abstract class DeckOfCards{
        public String draw(){
            return "A♠";
        }
    }

    class CircleDeckOfCards extends DeckOfCards implements Circle{}

    /**
     * The class wins, no need to disambiguate.
     */
    @Test public void test(){
        CircleDeckOfCards circleDeckOfCards = new CircleDeckOfCards();
        assertEquals("A♠", circleDeckOfCards.draw());
    }
}
