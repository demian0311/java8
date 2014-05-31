package com.neidetcher.java8._00_nonfpfeatures.optional;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalMotivation {

    @Test public void usingOptionalPresent() {
        Optional<String> stringOptional = Optional.of("Hello");
        assertTrue(stringOptional.isPresent());
        assertEquals("Hello", stringOptional.get());
        assertEquals("Hello", stringOptional.orElse("world"));
        stringOptional.ifPresent(s -> assertEquals("Hello", s));
    }

    @Test public void usingOptionalNotPresent() {
        Optional<String> stringOptional = Optional.empty();

        assertFalse(stringOptional.isPresent());
        assertEquals("world", stringOptional.orElse("world"));
        stringOptional.ifPresent(s -> fail("not present"));

        try {
            stringOptional.get();
            fail("should throw an NSEE");
        }catch(NoSuchElementException nsee){
            assertEquals("No value present", nsee.getMessage());
        }
    }

    @Test public void optionalEquality() {
        Optional<String> fooName = Optional.of("Foo");
        Optional<String> fooName2 = Optional.of("Foo");
        Optional<String> barName = Optional.of("Bar");

        assertEquals(fooName, fooName2);
        assertEquals(fooName.hashCode(), fooName2.hashCode());

        assertNotEquals(fooName, barName);
        assertNotEquals(fooName.hashCode(), barName.hashCode());
    }


    @Test(expected = NullPointerException.class)
    public void usingOptionalNull() {
        Optional.of(null);
    }

    @Test public void ofNullable() {
        Optional<String> stringOptional = Optional.ofNullable(null);

        assertFalse(stringOptional.isPresent());
        assertEquals("world", stringOptional.orElse("world"));
        stringOptional.ifPresent(s -> fail("not present"));
    }

    @Test public void streamFindFirst() {
        List<String> stringList = Arrays.asList("AAA", "BBB", "CCC");
        Optional<String> firstString = stringList.stream().findFirst();
        assertEquals(Optional.of("AAA"), firstString);
    }

    public class Person {
        public final String name;
        public final LocalDate birthDate;
        public final Optional<LocalDate> deathDate;

        public Person(
                String nameIn,
                LocalDate birthDateIn,
                Optional<LocalDate> deathDateIn){
            name = nameIn;
            birthDate = birthDateIn;
            deathDate = deathDateIn;
        }
    }

    @Test public void optionalInUse(){
        Person p = new Person(
                "Fred",
                LocalDate.parse("1971-12-04"),
                Optional.empty());
    }
}
