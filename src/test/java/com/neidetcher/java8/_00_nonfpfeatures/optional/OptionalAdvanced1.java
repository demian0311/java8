package com.neidetcher.java8._00_nonfpfeatures.optional;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalAdvanced1 {


    public Optional<String> getUserNameByEmail(String emailIn){
        return Optional.of("helloworld");
    }

    public Optional<Integer> getAccountNumberByUserName(String userNameIn){
        return Optional.of(12353);
    }

    public Optional<List<String>> getChannelsForAccountNumber(String accountNumberIn){
        return Optional.of(Arrays.asList("AAA", "BBB", "CCC"));
    }

    @Test
    public void test(){
        /*
        Optional<Integer> foo = getUserNameByEmail("hello@world.com")
                .flatMap(String userName -> getAccountNumberByUserName(userName))
                .flatMap(String accountNumber -> getChannelsForAccountNumber(accountNumber));
                //.map(ii -> ii.toString());
                */

    }

}
