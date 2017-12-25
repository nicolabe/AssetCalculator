package com.nicolabe;

public class Asset {
    String name;
    String ticker;
    double quantity;
    Exchange exchange;

    public Asset(String name, String ticker, double quantity, Exchange exchange) {
        this.name = name;
        this.ticker = ticker;
        this.quantity = quantity;
        this.exchange = exchange;
    }

    public String getUrl() {
        return this.exchange.getUrl(this.ticker);
    }

    public String getName() {
        return this.name;
    }

    public double getQuantity() {
        return this.quantity;
    }
}
