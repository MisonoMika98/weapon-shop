package com.pluralsight.util;

import com.pluralsight.models.*;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ReceiptWriter
{
    public static void writeOrder(Order order)
    {
        try (FileOutputStream fileOutputStream = new FileOutputStream("data/orders.csv", true);
             PrintWriter printWriter = new PrintWriter(fileOutputStream))
        {
            for (OrderValuable item : order.getValuables())
            {
                printWriter.print(item.getName() + "|$" + item.getPrice() + "|");
            }
            printWriter.print("$" + order.getTotal() + "|#" + order.getOrderNumber()); // outside the for loop so the order # doesn't repeat
            printWriter.println(); // breaks onto next line inside .csv

        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
