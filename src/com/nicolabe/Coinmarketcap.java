package com.nicolabe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Coinmarketcap implements Exchange {
    String url;

    public Coinmarketcap() {
        this.url = "https://api.coinmarketcap.com/v1/ticker/";
    }

    public String getUrl(String ticker) {
        return this.url + ticker;
    }

    public double parseResponse(String response) {
        System.out.println(response);
        Pattern pattern = Pattern.compile("price_usd\": \"(\\d+\\.\\d+)");
        Matcher matcher = pattern.matcher(response);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        } else {
            return 0;
        }
    }
}
