package com.nicolabe;

public interface Exchange {
    String getUrl(String reprCode);
    double parseResponse(String response);
}
