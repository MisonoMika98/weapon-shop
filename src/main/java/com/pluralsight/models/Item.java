package com.pluralsight.models;

public class Item implements OrderValuable
{
    private String name;
    private double price;

    public Item(String name, double price)
    {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // used in ReceiptWriter
    @Override
    public String getDetails()
    {
        return "Item: " + name + " | Price: $" + price;
    }
}
