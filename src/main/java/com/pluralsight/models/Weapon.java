package com.pluralsight.models;

public class Weapon implements OrderValuable
{
    private String name;
    private String weaponType;
    private String color;
    private double price;
    private Enhancement enhancement;

    public Weapon(String name, String weaponType, String color, double price, Enhancement enhancement)
    {
        this.name = name;
        this.weaponType = weaponType;
        this.color = color;
        this.price = price;
        this.enhancement = enhancement;
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
        return price + enhancement.getPrice();
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Enhancement getEnhancement() {
        return enhancement;
    }

    public void setEnhancement(Enhancement enhancement) {
        this.enhancement = enhancement;
    }
}
