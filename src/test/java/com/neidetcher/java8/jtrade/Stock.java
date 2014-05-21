package com.neidetcher.java8.jtrade;

import java.util.Arrays;
import java.util.List;

public class Stock implements Commodity{
    final String ticker;
    final Double price;
    final Integer quantity;
    final String exchange;
    // TODO: include an Optional field

    public Stock(String tickerIn, Double priceIn, Integer quantityIn, String exchangeIn){
        ticker = tickerIn;
        price = priceIn;
        quantity = quantityIn;
        exchange = exchangeIn;
    }

    public String getTicker(){ return ticker; }
    public @Override Double getPrice(){ return price; }
    public @Override Integer getQuantity() { return quantity;}
    public String getExchange(){ return exchange; }

    public String toString(){
        return String.format("Stock(\"%s\", %s, %s, %s)", ticker, price, quantity, exchange);
    }

    // TODO: find out the actual exchange that these are on
    public static List<Stock> portfolio = Arrays.asList(
            new Stock("TWC", 135.71, 68, "DOW"),
            new Stock("LVLT", 33.96, 22, "NASDAQ"),
            new Stock("GOOG", 1_150.53, 17, "NASDAQ"),
            new Stock("AAPL", 540.67, 30, "NASDAQ"),
            new Stock("MSFT", 36.38, 87, "DOW"),
            new Stock("ORCL", 38.21, 26, "DOW"));
}
