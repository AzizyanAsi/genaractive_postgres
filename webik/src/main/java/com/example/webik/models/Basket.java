package com.example.webik.models;

import java.util.ArrayList;

public class Basket {
   private ArrayList<Item> items;

    public ArrayList<Item> getItems() {
        return items;
    }

    public Basket() {
        this.items = new ArrayList<>();
    }

    public void addToBasket(Item item) {
        items.add(item);
    }

    public double calculatePrice() {
        double price = 0.0;
        for (Item item : items) {
            price += item.calculatePrice();
        }
        return price;
    }
}
