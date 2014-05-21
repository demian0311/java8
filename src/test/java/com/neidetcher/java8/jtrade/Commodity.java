package com.neidetcher.java8.jtrade;

public interface Commodity {
    public Double getPrice();
    public Integer getQuantity();

    public default Double getValue(){
        return getPrice() * getQuantity();
    }

    // TODO: include a static method
}
