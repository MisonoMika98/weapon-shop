package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

public class Order
{
    private List<OrderValuable> valuables = new ArrayList<>();
    private int orderNumber;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void addValuable(OrderValuable valuable)
    {
        valuables.add(valuable);
    }

    public void removeValuable(OrderValuable valuable)
    {
        valuables.remove(valuable);
    }

    public List<OrderValuable> getValuables()
    {
        return valuables;
    }


    // helper method that adds all the OrderValuable prices together using a stream
    public double getTotal()
    {
        return valuables.stream()
                .mapToDouble(item -> item.getPrice()) // converts all items into doubles to get their price
                .sum(); // adds everything

    }
}
