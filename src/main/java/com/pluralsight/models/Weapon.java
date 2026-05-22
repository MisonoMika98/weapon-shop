package com.pluralsight.models;

public class Weapon implements OrderValuable
{
    private String name;
    private String weaponType;
    private String color;
    private double price;

    public Weapon(String name, String weaponType, String color, double price)
    {
        this.name = name;
        this.weaponType = weaponType;
        this.color = color;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
