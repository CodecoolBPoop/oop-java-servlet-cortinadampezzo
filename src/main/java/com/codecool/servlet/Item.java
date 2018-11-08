package com.codecool.servlet;

public class Item {

    private static int counter = 0;
    private int id;
    private String name;
    private double price;

    public Item(String name, double price) {
        this.id = counter++;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}
