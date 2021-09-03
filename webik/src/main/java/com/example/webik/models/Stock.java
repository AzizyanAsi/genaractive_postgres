package com.example.webik.models;

public class Stock extends Item {
    public Stock() {
    }

    public Stock(String id, String name, String imageUrl, double price, Configuration.Resolution resolution) {
        super(id, name, imageUrl, price, new Configuration(resolution));
    }

    public Stock(String id, String name, double price, Configuration configuration) {
        super(id, name, null, price, configuration);
    }

    public Stock(String[] values, Configuration.Resolution resolution) {
        super(values[0],values[2],values[3], Double.parseDouble(values[1]),new Configuration(resolution));
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

}