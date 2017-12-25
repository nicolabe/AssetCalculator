package com.nicolabe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Coinbase implements Exchange {
    String url;

    public Coinbase() {
        this.url = "https://api.coinbase.com/v2/prices/";
    }

    public String getUrl(String ticker) {
        return this.url + ticker + "/spot";
    }

    public double parseResponse(String response) {
        Pattern pattern = Pattern.compile("amount\":\"(\\d+\\.\\d+)");
        Matcher matcher = pattern.matcher(response);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        } else {
            return 0;
        }
    }
}
