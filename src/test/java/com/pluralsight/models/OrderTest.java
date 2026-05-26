package com.pluralsight.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderGetPriceStreamTest
{
    Order testOrder = new Order();
    Enhancement testEnhancement = new Enhancement("Fire", 100);
    Weapon testWeapon = new Weapon("Bow", "Ranged", "Blue", 675, testEnhancement);
    Item testItem = new Item("Smoke Ball", 200);
    Potion testPotion = new Potion("Elixir", 1000);

    @Test
    void getPriceStreamTest()
    {
        // arrange
        testOrder.addValuable(testWeapon);
        testOrder.addValuable(testItem);
        testOrder.addValuable(testPotion);
        double expected = 1975;

        // act
        var actual = testOrder.getTotal();

        // assert
        assertEquals(expected, actual, "it should add the total of all items together");

    }

}