package com.nicolabe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties prop = new Properties();
        InputStream input = null;
        Coinbase coinbase = new Coinbase();
        List<Asset> assetList = new ArrayList<Asset>();

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("database"));
            System.out.println(prop.getProperty("dbuser"));
            System.out.println(prop.getProperty("dbpassword"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Asset ether = new Asset("Ether", "ETH-USD", 0, coinbase);
        Asset bitcoin = new Asset("Bitcoin", "BTC-USD", 0, coinbase);
        Asset bitcoinCash = new Asset("BitcoinCash", "BCH-USD", 0, coinbase);
        assetList.add(ether);
        assetList.add(bitcoin);
        assetList.add(bitcoinCash);

        Client client;
        double totalValue = 0;
        for (Asset asset : assetList) {
            try {
                client = new Client(asset.getUrl());
            } catch(IOException e) {
                System.err.println("Failed to initialize client " + e);
                continue;
            }
            double price;
            try {
                String response = client.doGetRequest();
                price = asset.exchange.parseResponse(response);
            } catch(IOException e) {
                System.err.println("Failed to get url " + asset.getName() + ". Error: " + e);
                price = 0;
            }
            double value = price * asset.getQuantity();
            totalValue += value;
            System.out.println(asset.getName() + ": $" + value);
        }
        System.out.println("Total value: $" + totalValue);
    }
}
