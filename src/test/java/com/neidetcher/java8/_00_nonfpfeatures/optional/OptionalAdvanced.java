package com.neidetcher.java8._00_nonfpfeatures.optional;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalAdvanced {
    class Address{
        String street1;
        String street2;
        String city;
        String state;
        String zip;

        public Address(
            String street1In,
            String street2In,
            String cityIn,
            String stateIn,
            String zipIn){
        street1 = street1In;
        street2 = street2In;
        city = cityIn;
        state = stateIn;
        zip = zipIn;
        }

        String getStreet1(){ return street1 ; }
        String getStreet2(){ return street2; }
        String getCity(){ return city; }
        String getState(){ return state; }
        String getZip(){ return zip; }
    }

    class Item{
        private String sku;
        private Integer quantity;
        private Double price;
        public Optional<LocalDate> dateShipped;
        public Optional<Address> address;

        public Item(String skuIn, Integer quantityIn, Double priceIn, Optional<LocalDate> dateShippedIn){
            sku = skuIn;
            quantity = quantityIn;
            price = priceIn;
            dateShipped = dateShippedIn;
        }

        public String getSku(){ return sku;}
        public Integer getQuantity(){ return quantity;}
        public Double getPrice(){ return price;}
        public Double getTotalPrice(){ return price * quantity;}
        public Optional<LocalDate> getDateShipped(){ return dateShipped;}
    }

    class Order{
        public Integer id;
        public List<Item> items;
        public LocalDate dateOrdered;

        public Order(Integer idIn, List<Item> itemsIn, LocalDate dateOrderedIn){
            id = idIn;
            items = itemsIn;
            dateOrdered = dateOrderedIn;
        }

        public Integer getId(){ return id;}
        public List<Item> getItems(){ return items;}
        public LocalDate getDateOrdered(){ return dateOrdered;}
        //public Boolean orderShipped(){
        //    return items.stream().allMatch(ii -> ii.getDateShipped().isPresent());
        //}
    }

    @Test
    public void test(){
        List<Item> items = Arrays.asList(
                new Item("123", 2, 80.23, Optional.empty()),
                new Item("ABC", 1, 24.38, Optional.empty()),
                new Item("CCC", 10, 9.87, Optional.empty()));
        Order order = new Order(407, items, LocalDate.now());

        assertFalse(order.getItems().stream().allMatch(ii -> ii.getDateShipped().isPresent()));
    }
}
