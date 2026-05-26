package com.pluralsight.util;

import com.pluralsight.models.*;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ReceiptWriter
{
    public static void writeOrder(Order order)
    {
        try (FileOutputStream fileOutputStream = new FileOutputStream("orders.csv", true);
             PrintWriter printWriter = new PrintWriter(fileOutputStream))
        {
            for (OrderValuable item : order.getValuables())
            {
                printWriter.println(item.getName() + "|" + item.getPrice());
            }

        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
