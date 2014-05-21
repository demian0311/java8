package com.neidetcher.java8._00_nonfpfeatures;

import com.neidetcher.java8.jtrade.Stock;
import org.junit.Test;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

public class OptionalTest {

    @Test public void usingOptional() {
        Optional<String> s = Optional.of("Hello World!");
        Optional<String> s1 = Optional.empty();

        if(s.isPresent()) {
            assertEquals("Hello World!", s.get());
        }
    }


    @Test public void optionalInCollections(){
        Optional<Stock> firstStock = Stock.portfolio
                .stream()
                .findFirst();

        if(firstStock.isPresent()) {
            assertEquals("TWC", firstStock.get().getTicker());
        }else {
            fail("there should have been a first stock");
        }
    }

    @Test public void dontCallGetWithoutChecking() {
        Optional<String> name = Optional.empty();
        try{
            name.get();
            fail("should have thrown an exception");
        } catch(NoSuchElementException e){
            System.out.println("e: " + e.getMessage());
            // calling .get() on an empty Optional throws
            // a NoSuchElementException
        }
    }

    @Test public void optionalEquality() {
        Optional<String> name = Optional.of("Foo");
        Optional<String> name2 = Optional.of("Foo");
        Optional<String> name3 = Optional.of("Bar");

        assertEquals(name, name2);
        assertEquals(name.hashCode(), name2.hashCode());

        assertNotEquals(name2, name3);
        assertNotEquals(name2.hashCode(), name3.hashCode());
    }


    @Test public void optionalsInBeans() {
        Person p = new Person(
                "Demian",
                Optional.empty(),
                "Neidetcher",
                new Date());
    }

    // TODO: show how we get an NPE when we try to create an Optional.of(null)
    /*
    @Test public void optionalsInBeans() {
        Person p = new Person(
                "Demian",
                Optional.of(null), //empty(),
                "Neidetcher",
                new Date());
    }*/
}

class Person{
    String firstName;
    Optional<String> middleName;
    String lastName;
    Date dateOfBirth;

    Person(String firstName,
           Optional<String> middleName,
           String lastName,
           Date dateOfBirth) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public Optional<String> getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean isValid(){
        return (this.firstName != null && this.lastName != null && this.dateOfBirth != null);
    }

    public String getFullName(){
        return firstName + (middleName.isPresent()? middleName: "") + lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", middleName=" + middleName +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!dateOfBirth.equals(person.dateOfBirth)) return false;
        if (!firstName.equals(person.firstName)) return false;
        if (!lastName.equals(person.lastName)) return false;
        if (!middleName.equals(person.middleName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + middleName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        return result;
    }
}
