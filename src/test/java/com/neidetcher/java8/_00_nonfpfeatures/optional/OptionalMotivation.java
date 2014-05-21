package com.neidetcher.java8._00_nonfpfeatures.optional;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class OptionalMotivation {

    class Employee0{
        Integer id;
        String name;
        LocalDate hireDate;
        LocalDate terminationDate;

        public Employee0(Integer idIn, String nameIn, LocalDate hireDateIn, LocalDate terminationDateIn){
            id = idIn;
            name = nameIn;
            hireDate = hireDateIn;
            terminationDate = terminationDateIn;
        }

        public long numberOfDaysEmployed(){
            LocalDate effectiveTerminationDate = terminationDate == null? LocalDate.now(): terminationDate;
            return hireDate.until(effectiveTerminationDate, ChronoUnit.DAYS);
        }
    }

    @Test public void test0(){
        Employee0 employee0 = new Employee0(1, "Foo Bar", LocalDate.now().minusDays(20), null);
        assertEquals(20, employee0.numberOfDaysEmployed());
    }

    class Employee1{
        Integer id;
        String name;
        LocalDate hireDate;
        Optional<LocalDate> terminationDate;

        public Employee1(Integer idIn, String nameIn, LocalDate hireDateIn, Optional<LocalDate> terminationDateIn){
            id = idIn;
            name = nameIn;
            hireDate = hireDateIn;
            terminationDate = terminationDateIn;
        }

        public long numberOfDaysEmployed(){
            return hireDate.until(terminationDate.orElse(LocalDate.now()), ChronoUnit.DAYS);
        }
    }

    @Test public void test1(){
        Employee1 employee1 = new Employee1(1, "Foo Bar", LocalDate.now().minusDays(20), Optional.empty());
        assertEquals(20, employee1.numberOfDaysEmployed());
    }


}
