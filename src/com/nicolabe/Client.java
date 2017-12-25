package com.nicolabe;

import java.net.*;
import java.io.*;

public class Client {
    URL url;

    public Client(String url) throws IOException {
        this.url = new URL(url);
    }

    public String doGetRequest() throws IOException {
        URLConnection yc = this.url.openConnection();
        BufferedReader in = new BufferedReader(
        new InputStreamReader(
        yc.getInputStream()));
        String inputLine;
        String response = "";
        while ((inputLine = in.readLine()) != null)
            response += inputLine;
        in.close();
        return response;
    }
}
