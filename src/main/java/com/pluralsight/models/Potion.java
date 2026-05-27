package com.pluralsight.models;

public class Potion implements OrderValuable
{
    private String name;
    private double price;

    public Potion(String name, double price)
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


    // user in ReceiptWriter
    @Override
    public String getDetails()
    {
        return "Potion: " + name + " | Price: $" + price;
    }
}
