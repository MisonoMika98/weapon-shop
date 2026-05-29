package com.pluralsight.util;

import com.pluralsight.models.*;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// one thing I have left to add is a way to make it so it prints additional weapon info (enhancement + color)
public class ReceiptWriter
{
    public static void writeOrder(Order order)
    {
        // shoutout to Omar M. for helping me with this class
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String formattedDate = date.format(formatter);

        try (FileOutputStream fileOutputStream = new FileOutputStream("receipts/" + formattedDate + ".txt");
             PrintWriter printWriter = new PrintWriter(fileOutputStream))
        {
            printWriter.println("==========================================");
            printWriter.println("           THE BLACKSMITH SHOP           ");
            printWriter.println("==========================================");
            printWriter.println("Order #: " + order.getOrderNumber());
            printWriter.println("Order Date: " + formattedDate);
            printWriter.println("------------------------------------------");
            for (OrderValuable item : order.getValuables())
            {
                printWriter.println(item.getDetails());
                printWriter.println("------------------------------------------");
            }
            printWriter.println("Total Price: $" + order.getTotal());
            printWriter.println("==========================================");
            printWriter.println("      Thank you for shopping with ME      ");
            printWriter.println("==========================================");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
