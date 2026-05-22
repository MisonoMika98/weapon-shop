package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

public class Order
{
    private List<OrderValuable> valuables = new ArrayList<>();


    public void addValuable(OrderValuable valuable)
    {
        valuables.add(valuable);
    }

    public void removeValuable(OrderValuable valuable)
    {
        valuables.remove(valuable);
    }

    public  List<OrderValuable> getValuables()
    {
        return valuables;
    }


    // WIP, will try to use stream instead of for loop
    public double getTotal()
    {
        double total = 0;
        return total;
    }
}
