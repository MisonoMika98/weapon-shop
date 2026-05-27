package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

public class Order
{
    // initializes a static counter that starts at 1
    private static int orderCounter = 1;
    private List<OrderValuable> valuables = new ArrayList<>();
    private int orderNumber;

    public Order()
    {
        // automatically keeps track of order #'s going up 1 by 1
        this.orderNumber = orderCounter++;
    }

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
                 // converts all items into doubles to get their price
                .mapToDouble(item -> item.getPrice())
                // adds everything
                .sum();

    }
}
