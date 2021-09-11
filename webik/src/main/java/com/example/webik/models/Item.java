package com.example.webik.models;


import java.util.Currency;
import java.util.Locale;

public abstract class Item {
    protected String id;
    protected String name;
    protected double price;
    protected Currency currency;
    protected Configuration configuration;
    private Group parentGroup;
    private String imageUrl;

    public Item() {

    }

    public Item(String id, String name, double price, Configuration configuration) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.configuration = configuration;
        this.currency = Currency.getInstance(Locale.getDefault());
    }

    public Item(String id, String name,  String url, double price, Configuration configuration) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.configuration = configuration;
        this.currency = Currency.getInstance(Locale.getDefault());
        this.imageUrl = url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append('{')
                .append(id)
                .append("_")
                .append(name)
                .append("_")
                .append(calculatePrice())
                .append("_")
                .append(currency)
                .append("_")
                .append(configuration.resolution.name())
                .append("_")
                .append(getImageUrl())
                .append("}").toString();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Group getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(Group parentGroup) {
        this.parentGroup = parentGroup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double calculatePrice() {
        return price * configuration.resolution.getCoefficient();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
