package com.pluralsight.util;

import com.pluralsight.models.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptWriterTest
{
    @Test
    void writeReceipt()
    {
        // arrange
        Order testOrder = new Order();
        Enhancement testEnhancement = new Enhancement("Fire", 100);
        Weapon testWeapon = new Weapon("Bow", "Ranged", "Blue", 675, testEnhancement);
        Item testItem = new Item("Smoke Ball", 200);
        Potion testPotion = new Potion("Elixir", 1000);

        testOrder.addValuable(testWeapon);
        testOrder.addValuable(testItem);
        testOrder.addValuable(testPotion);

        // act
        ReceiptWriter.writeOrder(testOrder);

        // assert
    }

}