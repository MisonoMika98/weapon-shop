package com.pluralsight.ui;

import com.pluralsight.models.*;
import com.pluralsight.util.ReceiptWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UserInterface
{
    // colors
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String RESET = "\u001B[0m";


    private static Scanner userInput = new Scanner(System.in);
    private static Order order = new Order();


    public static void homeScreen()
    {
        while (true)
        {
            System.out.println();
            System.out.println("================================");
            System.out.println("       THE BLACKSMITH SHOP      ");
            System.out.println("================================");
            System.out.println("  1) New Order");
            System.out.println("  2) View Past Receipts");
            System.out.println("  0) Exit");
            System.out.println("================================");
            System.out.println();


            String choice = getUserInput("Select an option here: ");

            switch (choice)
            {
                case "1":
                    orderScreen();
                    break;

                case "2":
                    pastReceiptsScreen();
                    break;

                case "0":
                    System.out.println();
                    System.out.println("See you again soon!");
                    System.exit(0);

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }
    }




    public static void pastReceiptsScreen()
    {
        while (true)
        {
            System.out.println();
            System.out.println("================================");
            System.out.println("        Past Receipts           ");
            System.out.println("================================");
            // first time ever using the File object
            // this block of code will read all the files inside the folder using an array
            File receiptsFolder = new File("receipts/");
            File[] receipts = receiptsFolder.listFiles();

            // if block that checks if the folder has any receipts in it at all
            if (receipts.length == 0)
            {
                System.out.println("No receipts found.");
                System.out.println();
                System.out.println("Returning to home screen...");
                System.out.println();
                return;
            }


            // reused logic/code from my removeSingleValuableFromOrderScreen();
            // have to use length instead of size() since it's an array
            for (int i = 0; i < receipts.length; i++)
            {
                // counts up automatically, for each individual valuable using the index and prints it in a line
                System.out.println((i + 1) + ") " + receipts[i].getName());
            }
            System.out.println("0) Go Back");
            System.out.println("================================");


            String choice = getUserInput("Select a receipt to view: ");
            if (choice.equals("0"))
            {
                System.out.println();
                System.out.println("Going back to home screen...");
                return;
            }

            // first try block that's used to catch any errors with parseInt
            try
            {
                // more reused code/logic from removeSingleValuableFromOrderScreen();
                int index = Integer.parseInt(choice) - 1;
                if (index >= 0 && index < receipts.length)
                {
                    // if block that has a try block inside wow, used to catch any errors with the readers
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(receipts[index])))
                    {
                        System.out.println();
                        String line;
                        // reads every line inside the folder until there's no more
                        while ((line = bufferedReader.readLine()) != null)
                        {
                            System.out.println(line);
                        }
                    }
                }
                // purpose of this else is to print something if the user inputs a number that doesn't match a receipt
                else
                {
                    System.out.println("Invalid option. Please try again.");
                }
            }
            // catch everything, instead of just numberformatexception because of the fileScanner
            catch (Exception e)
            {
                System.out.println("Something went wrong. Please try again or check your file directory");
                System.out.println(e.getMessage());
            }

        }

    }




    public static void orderScreen()
    {
        while (true)
        {
        System.out.println();
        System.out.println("================================");
        System.out.println("           ORDER MENU           ");
        System.out.println("================================");
        System.out.println("1) Add New Weapon");
        System.out.println("2) Add Potion");
        System.out.println("3) Add Item");
        System.out.println("4) Checkout");
        System.out.println("0) Go Back");
        System.out.println("================================");
        System.out.println();


            String choice = getUserInput("Select an option: ");

            switch (choice)
            {
                case "1":
                    weaponSelectionScreen();
                    break;

                case "2":
                    potionSelectionScreen();
                    break;

                case "3":
                    itemSelectionScreen();
                    break;

                case "4":
                    checkoutScreen();
                    break;

                case "0":
                    System.out.println();
                    System.out.println("Going back to home screen...");
                    homeScreen();
                    break;

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }
    }




    public static void weaponSelectionScreen()
    {
        while (true)
        {
            System.out.println();
            System.out.println("==================================");
            System.out.println("        WEAPON TYPE SELECT        ");
            System.out.println("==================================");
            System.out.println("  1) Piercing Weapon  ($300)");
            System.out.println("  2) Blunt Weapon     ($250)");
            System.out.println("  3) Ranged Weapon    ($500)");
            System.out.println("----------------------------------");
            System.out.println("  Don't know what Weapon to create?");
            System.out.println("  Check out my signature weapons:");
            System.out.println("  4) Signature Weapons");
            System.out.println("----------------------------------");
            System.out.println("  0) Go Back");
            System.out.println("==================================");
            System.out.println();


            String selection = getUserInput("Select a weapon type here: ");
            switch (selection)
            {
                case "1":
                    System.out.println();
                    System.out.println("================================");
                    System.out.println("        PIERCING WEAPONS        ");
                    System.out.println("================================");
                    System.out.println("  A) Sword   ($100)");
                    System.out.println("  B) Katana  ($200)");
                    System.out.println("  C) Spear   ($300)");
                    System.out.println("  0) Go Back");
                    System.out.println("================================");

                    String choice = getUserInput("Select a weapon here: ");
                    switch (choice)
                    {
                        case "A":
                            enhancementSelectionScreen("Sword", "Piercing", 400);
                            break;

                        case "B":
                            enhancementSelectionScreen("Katana", "Piercing", 500);
                            break;

                        case "C":
                            enhancementSelectionScreen("Spear","Piercing", 1000);
                            break;

                        case "0":
                            weaponSelectionScreen();

                        default:
                            System.out.println();
                            System.out.println("Invalid option. Please try again.");
                            System.out.println();
                    }
                    break;

                case "2":
                    System.out.println();
                    System.out.println("================================");
                    System.out.println("         BLUNT WEAPONS          ");
                    System.out.println("================================");
                    System.out.println("  A) Staff   ($50)");
                    System.out.println("  B) Mace    ($150)");
                    System.out.println("  C) Hammer  ($225)");
                    System.out.println("  0) Go Back");
                    System.out.println("================================");

                    String choice2 = getUserInput("Select a weapon here: ");
                    switch (choice2)
                    {
                        case "A":
                            enhancementSelectionScreen("Staff","Blunt",300);
                            break;

                        case "B":
                            enhancementSelectionScreen("Mace","Blunt", 400);
                            break;

                        case "C":
                            enhancementSelectionScreen("Hammer","Blunt", 475);
                            break;

                        case "0":
                            weaponSelectionScreen();

                        default:
                            System.out.println();
                            System.out.println("Invalid option. Please try again.");
                            System.out.println();
                    }
                    break;

                case "3":
                    System.out.println();
                    System.out.println("================================");
                    System.out.println("         RANGED WEAPONS         ");
                    System.out.println("================================");
                    System.out.println("  A) Bow        ($175)");
                    System.out.println("  B) Gun        ($250)");
                    System.out.println("  C) Boomerang  ($50)");
                    System.out.println("  0) Go Back");
                    System.out.println("================================");

                    String choice3 = getUserInput("Select a weapon here: ");
                    switch (choice3)
                    {
                        case "A":
                            enhancementSelectionScreen("Bow","Ranged",675);
                            break;

                        case "B":
                            enhancementSelectionScreen("Gun","Ranged", 750);
                            break;

                        case "C":
                            enhancementSelectionScreen("Boomerang","Ranged", 550);
                            break;

                        case "0":
                            weaponSelectionScreen();

                        default:
                            System.out.println();
                            System.out.println("Invalid option. Please try again.");
                            System.out.println();
                    }
                    break;

                case "4":
                    signatureWeaponScreen();
                    break;


                case "0":
                    System.out.println();
                    System.out.println("Going back to order screen...");
                    return;

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();

            }
        }
    }




    public static void signatureWeaponScreen()
    {
        while (true)
        {
            System.out.println();
            System.out.println("=============================================================");
            System.out.println("               MY SIGNATURE WEAPON INVENTORY                 ");
            System.out.println("=============================================================");
            System.out.println();
            System.out.println("  1) Evanescence ($700)");
            System.out.println("     A single swing cuts through the boundary between");
            System.out.println("     reality and dreams. Veiled emotions spill from its");
            System.out.println("     everchanging blade, like droplets of transient dew");
            System.out.println("     from an empty shell.");
            System.out.println();
            System.out.println("  2) Hrunting ($600)");
            System.out.println("     This blade has drunk of so many veins, it's turned");
            System.out.println("     jet black. Yet oddly enough, the more it cuts, the");
            System.out.println("     sharper it gets.");
            System.out.println();
            System.out.println("  3) Vortex of the Void ($11000)");
            System.out.println("     Once borne by a god of war. One thrust emits a");
            System.out.println("     great current of air, generating a whirlwind of");
            System.out.println("     plaintive screams. Afterward only silence remains.");
            System.out.println();
            System.out.println("  4) Collapse of the Tower ($675)");
            System.out.println("     A hammer brought forth by the ill-fated tower.");
            System.out.println("     Its terrifying swing crushes enemies in a single blow.");
            System.out.println();
            System.out.println("  5) Staff of the Magi ($10300)");
            System.out.println("     A staff found in an ancient castle, long hidden from the");
            System.out.println("     world. It is said the soul of a great sage resides within.");
            System.out.println();
            System.out.println("  6) Mandragora ($700)");
            System.out.println("     A mace fashioned from the poisonous root");
            System.out.println("     of the mandragora.");
            System.out.println();
            System.out.println("  7) Nike Bow ($775)");
            System.out.println("     A bow blessed by the Goddess of Victory.");
            System.out.println();
            System.out.println("  8) Crimson Calamity ($1750)");
            System.out.println("     Despite its old-fashioned appearance, it always");
            System.out.println("     smells of flames and gunpowder.");
            System.out.println();
            System.out.println("  9) Stellarang ($10550)");
            System.out.println("     A blisteringly brutal boomerang that strikes enemies");
            System.out.println("     with the explosive strength of a supernova.");
            System.out.println();
            System.out.println("  0) Go Back");
            System.out.println("===========================================================");
            System.out.println();

            String choice = getUserInput("Select your signature weapon here: ");

            switch(choice)
            {
                case "1":
                    String name = "Evanescence";
                    String color = "Green";

                    Enhancement enhancement = new Enhancement("Wind", 200);
                    Weapon weapon = new Weapon(name, "Piercing", color, 500, enhancement);
                    order.addValuable(weapon);

                    System.out.println();
                    System.out.println(colorize(name, color) + " has been added to your order");
                    System.out.println();
                    System.out.println("Returning to order screen...");
                    System.out.println();
                    orderScreen();
                    return;

                case "2":
                    String name2 = "Hrunting";
                    String color2 = "Black";

                    Enhancement enhancement2 = new Enhancement("Earth", 200);
                    Weapon weapon2 = new Weapon(name2, "Piercing", color2, 400, enhancement2);
                    order.addValuable(weapon2);

                    System.out.println();
                    System.out.println(colorize(name2, color2) + " has been added to your order");
                    System.out.println();
                    System.out.println("Returning to order screen...");
                    System.out.println();
                    orderScreen();
                    return;

                case "3":
                    String name3 = "Vortex of the Void";
                    String color3 = "Cyan";

                    Enhancement enhancement3 = new Enhancement("Astral", 10000);
                    Weapon weapon3 = new Weapon(name3, "Piercing", color3, 1000, enhancement3);
                    order.addValuable(weapon3);

                    System.out.println();
                    System.out.println(colorize(name3, color3) + " has been added to your order");
                    System.out.println();
                    System.out.println("Returning to order screen...");
                    System.out.println();
                    orderScreen();
                    return;

                case "4":
                    String name4 = "Collapse of the Tower";
                    String color4 = "Black";

                    Enhancement enhancement4 = new Enhancement("Earth", 200);
                    Weapon weapon4 = new Weapon(name4, "Blunt", color4, 475, enhancement4);
                    order.addValuable(weapon4);

                    System.out.println();
                    System.out.println(colorize(name4, color4) + " has been added to your order");
                    System.out.println();
                    System.out.println("Returning to order screen...");
                    System.out.println();
                    orderScreen();
                    return;

                case "5":
                    String name5 = "Staff of the Magi";
                    String color5 = "Purple";

                    Enhancement enhancement5 = new Enhancement("Rune", 10000);
                    Weapon weapon5 = new Weapon(name5, "Blunt", color5, 300, enhancement5);
                    order.addValuable(weapon5);

                    System.out.println();
                    System.out.println(colorize(name5, color5) + " has been added to your order");
                    System.out.println();
                    System.out.println("Returning to order screen...");
                    System.out.println();
                    orderScreen();
                    return;

                case "6":
                    String name6 = "Mandragora";
                    String color6 = "Yellow";

                    Enhancement enhancement6 = new Enhancement("Wood", 300);
                    Weapon weapon6 = new Weapon(name6, "Blunt", color6, 400, enhancement6);
                    order.addValuable(weapon6);

                    System.out.println();
                    System.out.println(colorize(name6, color6) + " has been added to your order");
                    System.out.println();
                    System.out.println("Returning to order screen...");
                    System.out.println();
                    orderScreen();
                    return;

                case "7":
                    String name7 = "Nike Bow";
                    String color7 = "Purple";

                    Enhancement enhancement7 = new Enhancement("Electricity", 100);
                    Weapon weapon7 = new Weapon(name7, "Ranged", color7, 675, enhancement7);
                    order.addValuable(weapon7);

                    System.out.println();
                    System.out.println(colorize(name7, color7) + " has been added to your order");
                    System.out.println();
                    System.out.println("Returning to order screen...");
                    System.out.println();
                    orderScreen();
                    return;

                case "8":
                    String name8 = "Crimson Calamity";
                    String color8 = "Red";

                    Enhancement enhancement8 = new Enhancement("Armor Break", 1000);
                    Weapon weapon8 = new Weapon(name8, "Ranged", color8, 750, enhancement8);
                    order.addValuable(weapon8);

                    System.out.println();
                    System.out.println(colorize(name8, color8) + " has been added to your order");
                    System.out.println();
                    System.out.println("Returning to order screen...");
                    System.out.println();
                    orderScreen();
                    return;

                case "9":
                    String name9 = "Stellarang";
                    String color9 = "Blue";

                    Enhancement enhancement9 = new Enhancement("Astral", 10000);
                    Weapon weapon9 = new Weapon(name9, "Ranged", color9, 550, enhancement9);
                    order.addValuable(weapon9);

                    System.out.println();
                    System.out.println(colorize(name9, color9) + " has been added to your order");
                    System.out.println();
                    System.out.println("Returning to order screen...");
                    System.out.println();
                    orderScreen();
                    return;

                case "0":
                    System.out.println();
                    System.out.println("Going back to weapon selection screen...");
                    return;

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();

            }
        }
    }




    public static void enhancementSelectionScreen(String weaponName, String weaponType, double price)
    {
        while (true)
        {
            System.out.println();
            System.out.println("==================================");
            System.out.println("     ENHANCEMENT CATEGORIES       ");
            System.out.println("==================================");
            System.out.println("  1) Regular  ($100 - $300)");
            System.out.println("  2) Premium  ($500 - $10000)");
            System.out.println("  0) Go Back To Weapon Selection");
            System.out.println("==================================");
            System.out.println();


            // this fixes the bug I had that made color variable break after I added the Enhancement object
            String color = null;
            // see above
            String finalWeaponName = null;

            String choice = getUserInput("Select an enhancement category here: ");

            switch (choice)
            {
                case "1":
                    System.out.println();
                    System.out.println("================================");
                    System.out.println("     REGULAR ENHANCEMENTS       ");
                    System.out.println("================================");
                    System.out.println("  A) Fire        ($100)");
                    System.out.println("  B) Water       ($100)");
                    System.out.println("  C) Electricity ($100)");
                    System.out.println("  D) Wind        ($200)");
                    System.out.println("  E) Ice         ($200)");
                    System.out.println("  F) Earth       ($200)");
                    System.out.println("  G) Wood        ($300)");
                    System.out.println("  H) Poison      ($75)");
                    System.out.println("  I) Sleep       ($250)");
                    System.out.println("  J) Drain       ($250)");
                    System.out.println("  0) Go Back To Weapon Selection");
                    System.out.println("================================");
                    System.out.println();

                    String enhancementSelection = getUserInput("Select an Enhancement here: ");


                    switch (enhancementSelection)
                    {
                        case "A":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement = new Enhancement("Fire", 100);
                            Weapon weapon = new Weapon(finalWeaponName, weaponType, color, price, enhancement);
                            order.addValuable(weapon);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "B":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement2 = new Enhancement("Water", 100);
                            Weapon weapon2 = new Weapon(finalWeaponName, weaponType, color, price, enhancement2);
                            order.addValuable(weapon2);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "C":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement3 = new Enhancement("Electricity", 100);
                            Weapon weapon3 = new Weapon(finalWeaponName, weaponType, color, price, enhancement3);
                            order.addValuable(weapon3);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "D":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement4 = new Enhancement("Wind", 200);
                            Weapon weapon4 = new Weapon(finalWeaponName, weaponType, color, price, enhancement4);
                            order.addValuable(weapon4);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "E":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement5 = new Enhancement("Ice", 200);
                            Weapon weapon5 = new Weapon(finalWeaponName, weaponType, color, price, enhancement5);
                            order.addValuable(weapon5);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) +  " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "F":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement6 = new Enhancement("Earth", 200);
                            Weapon weapon6 = new Weapon(finalWeaponName, weaponType, color, price, enhancement6);
                            order.addValuable(weapon6);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "G":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement7 = new Enhancement("Wood", 300);
                            Weapon weapon7 = new Weapon(finalWeaponName, weaponType, color, price, enhancement7);
                            order.addValuable(weapon7);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "H":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement8 = new Enhancement("Poison", 75);
                            Weapon weapon8 = new Weapon(finalWeaponName, weaponType, color, price, enhancement8);
                            order.addValuable(weapon8);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "I":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement9 = new Enhancement("Sleep", 250);
                            Weapon weapon9 = new Weapon(finalWeaponName, weaponType, color, price, enhancement9);
                            order.addValuable(weapon9);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "J":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement10 = new Enhancement("Drain", 250);
                            Weapon weapon10 = new Weapon(finalWeaponName, weaponType, color, price, enhancement10);
                            order.addValuable(weapon10);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "0":
                            break;

                        default:
                            System.out.println();
                            System.out.println("Invalid option. Please try again.");
                            System.out.println();
                    }
                break;

                case "2":
                    System.out.println();
                    System.out.println("================================");
                    System.out.println("     PREMIUM ENHANCEMENTS       ");
                    System.out.println("================================");
                    System.out.println("  A) Dark        ($500)");
                    System.out.println("  B) Light       ($500)");
                    System.out.println("  C) Silence     ($700)");
                    System.out.println("  D) Armor Break ($1000)");
                    System.out.println("  E) Psychic     ($1500)");
                    System.out.println("  F) Nuclear     ($1500)");
                    System.out.println("  G) Crystal     ($3000)");
                    System.out.println("  H) Time        ($5000)");
                    System.out.println("  I) Rune        ($10000)");
                    System.out.println("  J) Astral      ($10000)");
                    System.out.println("  0) Go Back To Weapon Selection");
                    System.out.println("================================");
                    System.out.println();

                    String enhancementSelection2 = getUserInput("Select an Enhancement here: ");

                    switch (enhancementSelection2)
                    {
                        case "A":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement = new Enhancement("Dark", 500);
                            Weapon weapon = new Weapon(finalWeaponName, weaponType, color, price, enhancement);
                            order.addValuable(weapon);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "B":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement2 = new Enhancement("Light", 500);
                            Weapon weapon2 = new Weapon(finalWeaponName, weaponType, color, price, enhancement2);
                            order.addValuable(weapon2);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "C":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement3 = new Enhancement("Silence", 700);
                            Weapon weapon3 = new Weapon(finalWeaponName, weaponType, color, price, enhancement3);
                            order.addValuable(weapon3);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "D":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement4 = new Enhancement("Armor Break", 1000);
                            Weapon weapon4 = new Weapon(finalWeaponName, weaponType, color, price, enhancement4);
                            order.addValuable(weapon4);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "E":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement5 = new Enhancement("Psychic", 1500);
                            Weapon weapon5 = new Weapon(finalWeaponName, weaponType, color, price, enhancement5);
                            order.addValuable(weapon5);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "F":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement6 = new Enhancement("Nuclear", 1500);
                            Weapon weapon6 = new Weapon(finalWeaponName, weaponType, color, price, enhancement6);
                            order.addValuable(weapon6);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "G":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement7 = new Enhancement("Crystal", 3000);
                            Weapon weapon7 = new Weapon(finalWeaponName, weaponType, color, price, enhancement7);
                            order.addValuable(weapon7);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "H":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement8 = new Enhancement("Time", 5000);
                            Weapon weapon8 = new Weapon(finalWeaponName, weaponType, color, price, enhancement8);
                            order.addValuable(weapon8);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "I":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement9 = new Enhancement("Rune", 10000);
                            Weapon weapon9 = new Weapon(finalWeaponName, weaponType, color, price, enhancement9);
                            order.addValuable(weapon9);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "J":
                            color = colorSelectionScreen();
                            finalWeaponName = customWeaponNameScreen(weaponName);

                            easterEggCheck(finalWeaponName, weaponName);

                            Enhancement enhancement10 = new Enhancement("Astral", 10000);
                            Weapon weapon10 = new Weapon(finalWeaponName, weaponType, color, price, enhancement10);
                            order.addValuable(weapon10);
                            System.out.println();
                            System.out.println(colorize(finalWeaponName, color) + " has been added to your order");
                            System.out.println();
                            System.out.println("Returning to order screen...");
                            orderScreen();
                            return;

                        case "0":
                            break;

                        default:
                            System.out.println();
                            System.out.println("Invalid option. Please try again.");
                            System.out.println();
                    }
                break;

                case "0":
                    System.out.println();
                    System.out.println("Going back to weapon selection screen...");
                    return;

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }
    }




    public static String colorSelectionScreen()
    {
        while (true)
        {
        // uses the final color strings at the top of the class, RESET makes it so they don't bleed into the rest of the terminal's text
            System.out.println();
            System.out.println("================================");
            System.out.println("         COLOR SELECTION        ");
            System.out.println("================================");
            System.out.println("  0) No Color");
            System.out.println("  1) " + RED + "Red" + RESET);
            System.out.println("  2) " + BLUE + "Blue" + RESET);
            System.out.println("  3) " + GREEN + "Green" + RESET);
            System.out.println("  4) " + YELLOW + "Yellow" + RESET);
            System.out.println("  5) " + PURPLE + "Purple" + RESET);
            System.out.println("  6) " + CYAN + "Cyan" + RESET);
            System.out.println("  7) " + BLACK + "Black" + RESET);
            System.out.println("================================");
            System.out.println();


            String choice = getUserInput("Select your color here: ");

            switch (choice)
            {
                case "0":
                    return "No Color";

                case "1":
                    return "Red";

                case "2":
                    return "Blue";

                case "3":
                    return "Green";

                case "4":
                    return "Yellow";

                case "5":
                    return "Purple";

                case "6":
                    return "Cyan";

                case "7":
                    return "Black";

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }
    }




    // used to color the weapon's name when called
    public static String colorize(String text, String color)
    {
        switch (color)
        {
            case "No Color":
                return text;

            case "Red":
                return RED + text + RESET;

            case "Blue":
                return BLUE + text + RESET;

            case "Green":
                return GREEN + text + RESET;

            case "Yellow":
                return YELLOW + text + RESET;

            case "Purple":
                return PURPLE + text + RESET;

            case "Cyan":
                return CYAN + text + RESET;

            case "Black":
                return BLACK + text + RESET;

            default:
                return text;
        }
    }




    // uses most of the same logic as colorSelectionScreen() and colorize()
    public static String customWeaponNameScreen(String weaponName)
    {
        while (true)
        {
        System.out.println();
        System.out.println("Would you like to give your weapon a name?");
        System.out.println("Y) Yes");
        System.out.println("X) No");


            String choice = getUserInput("Make your choice here: ");

            switch (choice)
            {
                case "Y":
                    System.out.println();
                    return getUserWeaponName("Enter the name for your Weapon here: ");

                case "X":
                    // keeps weaponName as the default no name
                    return weaponName;

                default:
                System.out.println();
                System.out.println("Invalid option. Please try again.");
                System.out.println();
            }
        }
    }




    public static void potionSelectionScreen()
    {
        while (true)
        {
            System.out.println();
            System.out.println("================================");
            System.out.println("          POTION MENU           ");
            System.out.println("================================");
            System.out.println("  1) Small Potion  (50HP)");
            System.out.println("  2) Medium Potion (100HP)");
            System.out.println("  3) Large Potion  (300HP)");
            System.out.println("  4) Max Potion    (ALL HP)");
            System.out.println("  5) Elixir        (ALL HP + MP)");
            System.out.println("  0) Go Back");
            System.out.println("================================");
            System.out.println();


            String choice = getUserInput("Select your potion here: ");

            switch (choice)
            {
                case "1":
                    Potion potion = new Potion("Small Potion", 100);
                    order.addValuable(potion);
                    System.out.println();
                    System.out.println(potion.getName() + " has been added to your order");
                    System.out.println();
                    System.out.println("Returning to order screen...");
                    orderScreen();
                    return;

                case "2":
                    Potion potion2 = new Potion("Medium Potion", 200);
                    order.addValuable(potion2);
                    System.out.println();
                    System.out.println(potion2.getName() + " has been added to your order");
                    System.out.println();
                    System.out.println("Returning to order screen...");
                    orderScreen();
                    return;

                case "3":
                    Potion potion3 = new Potion("Large Potion", 300);
                    order.addValuable(potion3);
                    System.out.println();
                    System.out.println(potion3.getName() + " has been added to your order");
                    System.out.println();
                    System.out.println("Returning to order screen...");
                    orderScreen();
                    return;

                case "4":
                    Potion potion4 = new Potion("Max Potion", 500);
                    order.addValuable(potion4);
                    System.out.println();
                    System.out.println(potion4.getName() + " has been added to your order");
                    System.out.println();
                    System.out.println("Returning to order screen...");
                    orderScreen();
                    return;

                case "5":
                    Potion potion5 = new Potion("Elixir", 1000);
                    order.addValuable(potion5);
                    System.out.println();
                    System.out.println(potion5.getName() + " has been added to your order");
                    System.out.println();
                    System.out.println("Returning to order screen...");
                    orderScreen();
                    return;

                case "0":
                    System.out.println();
                    System.out.println("Going back to order screen...");
                    return;

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }
    }




    public static void itemSelectionScreen()
    {
        while (true)
        {
            System.out.println();
            System.out.println("================================");
            System.out.println("           ITEMS MENU           ");
            System.out.println("================================");
            System.out.println("  1) Smoke Ball     (Escapes Battle)   ($200)");
            System.out.println("  2) Teleport Stone (Escapes Dungeon)  ($200)");
            System.out.println("  3) Bomb           (Deals Damage)    ($200)");
            System.out.println("  0) Go Back");
            System.out.println("================================");
            System.out.println();


            String choice = getUserInput("Select your item here: ");

            switch (choice)
            {
                case "1":
                    Item item = new Item("Smoke Ball", 200);
                    order.addValuable(item);
                    System.out.println();
                    System.out.println(item.getName() + " has been added to your order");
                    System.out.println();
                    System.out.println("Returning to order screen...");
                    orderScreen();
                    return;

                case "2":
                    Item item2 = new Item("Teleport Stone", 200);
                    order.addValuable(item2);
                    System.out.println();
                    System.out.println(item2.getName() + " has been added to your order");
                    System.out.println();
                    System.out.println("Returning to order screen...");
                    orderScreen();
                    return;

                case "3":
                    Item item3 = new Item("Bomb", 200);
                    order.addValuable(item3);
                    System.out.println();
                    System.out.println(item3.getName() + " has been added to your order");
                    System.out.println();
                    System.out.println("Returning to order screen...");
                    orderScreen();
                    return;

                case "0":
                    System.out.println();
                    System.out.println("Going back to order screen...");
                    return;

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }
    }




    public static void checkoutScreen()
    {
        while (true)
        {
            System.out.println();
            System.out.println("================================");
            System.out.println("           YOUR ORDER           ");
            System.out.println("================================");

        // turns the list into a stream
        order.getValuables().stream()
                // makes it so ONLY Weapon objects go through the .filter, no items or potions allowed!
                .filter(item -> item instanceof Weapon)
                // .map transforms the items into Weapons, because of this I can access the colorize() method and use it inside of .forEach()
                .map(item -> (Weapon) item)
                // .forEach Weapon, prints
                .forEach(w -> System.out.println(colorize(w.getName(), w.getColor()) + ", $" + w.getPrice()));

        // turns the list into a stream
        order.getValuables().stream()
                // makes it so anything that's NOT a Weapon goes through the .filter, only potions and items!
                .filter(item -> !(item instanceof Weapon))
                // .forEach item that isn't a Weapon, prints
                .forEach(item -> System.out.println(item.getName() + ", $" + item.getPrice()));

        System.out.println("--------------------------------");
        // prints the total price using my getTotal() method
        System.out.println("  Total: $" + order.getTotal());
        System.out.println("================================");
        System.out.println();
        System.out.println("  Y) Confirm Order");
        System.out.println("  R) Remove a Valuable");
        System.out.println("  S) Show Order Again");
        System.out.println("  N) Cancel Order");
        System.out.println("  0) Go Back");
        System.out.println("================================");
        System.out.println();


        String choice = getUserInput("Make your selection here: ");

        switch (choice)
        {
            case "Y":
                System.out.println();
                System.out.println("Your Order has been confirmed");
                System.out.println();
                System.out.println("Printing Receipt......");
                System.out.println();
                ReceiptWriter.writeOrder(order);

                System.out.println("Would you like to view your receipt now?");
                System.out.println("Y) Yes");
                System.out.println("N) No");

                String selection = getUserInput("Choose here: ");
                switch(selection)
                {
                    case "Y":
                        // reuses code from the receiptwriter
                        System.out.println();
                        System.out.println("==========================================");
                        System.out.println("           THE BLACKSMITH SHOP           ");
                        System.out.println("==========================================");
                        System.out.println("Order #: " + order.getOrderNumber());
                        System.out.println("Order Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")));
                        System.out.println("------------------------------------------");
                        for (OrderValuable item : order.getValuables())
                        {
                            System.out.println(item.getDetails());
                            System.out.println("------------------------------------------");
                        }
                        System.out.println("Total Price: $" + order.getTotal());
                        System.out.println("==========================================");
                        System.out.println("     Thank you for shopping with ME      ");
                        System.out.println("==========================================");
                        System.out.println();


                        // wipes the order
                        order = new Order();
                        System.out.println();
                        System.out.println("Receipt printed, going back to home screen...");
                        System.out.println();
                        homeScreen();
                        return;

                    case "N":
                        // wipes the order
                        order = new Order();

                        System.out.println();
                        System.out.println("Receipt printed, going back to home screen...");
                        System.out.println();
                        homeScreen();
                        break;

                    default:
                        System.out.println();
                        System.out.println("Invalid option. Please try again.");
                        System.out.println();

                }
                break;

            case "R":
                removeSingleValuableFromOrderScreen();
                break;

            case "S":
                break;

            case "N":
                System.out.println();
                System.out.println("Your order has been canceled");
                // wipes the order
                order = new Order();
                System.out.println();
                System.out.println("Going back to home screen...");
                homeScreen();
                return;

            case "0":
                System.out.println();
                System.out.println("Going back to order screen...");
                orderScreen();
                break;

            default:
                System.out.println();
                System.out.println("Invalid option. Please try again.");
                System.out.println();
            }
        }
    }




    public static void removeSingleValuableFromOrderScreen()
    {
        while (true)
        {
        System.out.println();
        System.out.println("================================");
        System.out.println("   Select a Valuable to Remove  ");
        System.out.println("================================");

        // calls the list as a list (no stream)
        List<OrderValuable> valuables = order.getValuables();
        // prints the list using its index
        for (int i = 0; i < valuables.size(); i++)
        {
            // counts up automatically, for each individual valuable using the index and prints it in a line
            System.out.println((i + 1) + ") " + valuables.get(i).getDetails());
        }
        System.out.println("0) Go Back");


            String choice = getUserInput("Make your choice here: ");

            // goes back to the previous screen
            if (choice.equals("0"))
            {
                System.out.println();
                System.out.println("Going back to checkout screen...");
                return;
            }


            try
            {
                // subtracts the user's input by 1, so the user's input matches with the array's index
                // uses parseInt so it works with my helper method
                int index = Integer.parseInt(choice) - 1;
                if (index >= 0 && index < valuables.size())
                {
                    // prints to let the user know what got removed
                    System.out.println(valuables.get(index).getName() + " has been removed from your order");
                    System.out.println();
                    // removes the object from the list
                    order.removeValuable(valuables.get(index));
                }
                // if the user inputs a number that the array hasn't reached (i.e. order has 3 items, and they input 4)
                else
                {
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
                }
            }

            // makes it so if the user doesn't input a number, the program won't crash
            catch(NumberFormatException e)
            {
                System.out.println();
                System.out.println("Invalid format. Please enter a number and try again.");
                System.out.println();
            }
        }

    }




    // helper method, thx Gregor
    public static String getUserInput(String message)
    {
        System.out.print(message);
        return userInput.nextLine().trim().toUpperCase();
    }




    // helper method that exists only for the custom weapon name
    public static String getUserWeaponName(String message)
    {
        System.out.print(message);
        return userInput.nextLine();
    }



    // helper method that exists to make my easter eggs work
    // the reason why it checks for both weapon name strings is to see if the user picks the specific kind of weapon (like a sword or katana)
    // AND if they give it a custom name that triggers it

    // I wanted to do more, but I ran out of ideas and time, sad.
    public static void easterEggCheck(String finalWeaponName, String weaponName)
    {
        // created using official Blue Archive art via https://www.asciiart.eu/image-to-ascii
        String art = """
                ===============++==+##+++++++++++++++++++++++++++++*::*##***############-:::::::=:*++++++:::::::::::::::::::::::::...............:-=======--::::::::::....................
                ============++=++++##++++++++++++++++++++++++++++::-#####****###############%-:::.:=++++++::::::::::::::::::::::................:-=====--::::::::::::.....................
                ============+++++++#*+++++++++++++++++++++++++++::#######****####################=:++++++++::::::::::::::::::::................:-===--::::::::::::........................
                ==+====+=+++++++++=#+++++++++++++++++++++++*--:::################%%###############-*++++++++:::::::::::::::..................:--=--::::::::::::::.........................
                +====++++++++++++++#++++++++++++++++++++=---+++*::######%#####%%%################%-+**++++++*.:::::::::....................:----:::::::::::::.............................
                =+++++++++++++++++*++++++++++++++*=:::---++++####:-%%%%%%%####%%%################+-+++*++++++::::::.:.....................:--:::::::::::::................................
                ++++++++++++++++++*+++++++==+-::=-:::-::**###*+++#::%%%%%%####%##################-+++++*++++++.:.........................::::::::::::::...................................
                ++++++++++++++++++*+++++* -=:::::-----:=:*+++++++++::%%%%%######################--++++++++++++*........................:::::::::::::......................................
                +++++++*++++++++++*++*  =::-------------=:*++++++++=*::%%%%####################*....:++**++++++-....................::::::::::::..........................................
                +++++++*++++++++++*--*#%#*-:-------------=:*+++++++++#+::%%##################+--.........-++++++..................:::::::::...............................................
                +++++++*++++++++*-=**###%%%%%%%%#=:-------::=++*+++++++##.:+###############--+*++:...........+++#..............:::::::.................................=******............
                ++++++*#++++++*:+#***%%%%%%%%%%%%%%%%%#----:+++++++++++++*##---=*####*=---*:...:*++*-...........+............::::::.............................+****************#........
                +++++++#+++++::#######%%%%%%%%%%%%%%%%%%%%%:=+++++++++++++++*#####**+=:::........:+++++*:.........-.......:::::........=:............*...:***************************+....
                +++++++#+++*:###%#####%%%%%%%%%%%%%%%%%%%%%*-++++++++++++++++*++++++*..............-++*++##:........-..::::.....:..........+......*##*+++**++++++++++******************+%.
                +++++++#++:.##%%%%####%%%%%%%%%%%%%%%%%%%%%+-*++++*+++++++++++++++*++*...............++*+***+-........*................=......==#######++++++******+++++******************
                ++++++++++:##%%%%%####%%%%%%%%%%%%%%%%%%%%%:*#+++++*+++++++++++*+++#++.......####*=---+**+***++*........=....-:..........=......-########*+*++++++++++++++****************
                +++++++++-=%%%%%%%####%%%%%%%%%%%%%%%#####%-+##+++++*+++++++++++*+*+#++...#+............++***+++#+:.......:...:::+-........-:::::*...####*+++++++*#***++++++*******#******
                +++++++++::%%%%%%%#####%%%%%%%%%##########-*++#++++++*++++++++++**+*+#+*+................+**...+*##=:.......:.*:-...-=....::=-::::.....-##++++++++++++++++++++************
                +++++++++#.:#%%%%%#####%%%%%%############-++++##*+++++++++++++**++***+#+-.................+-..:.#*##.=:.........=....=-+:::::-::::=.....::#*++++++++++++++++++++****%*****
                ++++++++++*+::%%%%%####%%%%#############-#+++++#**+++++-+++++++*********+................:*..-...**#...:..............::*::::::=-:-.......-#++++++++++++++++++++++**%%****
                +++++++++++*++:#%%%####%%%############--#+++++++#-*+++++:+++*+****+++*+=+*.....:................=-**#...-......=.....-:::=-::::::.........-:+++++++++++++#*+++++++++%%****
                ++++++++++++*++=-#%####%###########=-*#+++++++++*#:++++++:*++****+++++++:+......*....................*...:.............::::*:::::.........::-+++++++++**+++++++++++++*****
                +++*+++++++++*+++#--+##########=--*##+++++++++*+++#:-+++++:+***++*+*+*++++*.......:##::.................-.:...-..........::::.#--.........:::+++++++++****++++++++++++****
                ++++++++++++++*++++*###+=--=+####**++++*+++++++++*+#::*++++::+++++++++*++-+....=#######+**:-*-............-..........=:...:....:::-......:::::+++++++++*#**+++++++++++****
                +++++++++++++++*++++++*+#+*+*+*****++++*****++***++**::*****=:+++++++++*++:=..####**%:::=**.=.:..#-........:..........::...........::....:::::-++++++++****+++++++++******
                ++++*+++++++++++*++++++*##*+**********************+*++.:-**++*::*++++++++....##* ===#...+...:=...#####:.....-........................:...+:::::*++++++++***++++++++++*****
                ++++++++++++*++++*+******#****************=*++****++=+=..:*++++..:*++-......**+=*====...-....:-..######- *................................:::::*++++++++**+++++++++++*****
                ++++++++++++**************#*+**************:++*++++++-++...-++++*..........+#====:::-........-..########..................................*:::+++++++*++*+++++++++++******
                +++++*++++*******************:**************:=++++++++=+*...................:=::::::+........:#########**....=::..........................+:*++++++++++++++++++**+++******
                +++++++++++*******************:***********++*.:*+++++++*.....................+::-+............##########**.....-..........................#++++++++++**++++++++++++*******
                ++++++*+++*********************:-******++++*++*.-++++::-......................................%##########*......*.......................=+++++********++++++++++++*******%
                *++++++++**********************..:+*:++*+++++*++=*##*+:........................................###########*.=.....:...................++*************+#++++++++++******%%%
                #++++++*+***********************...:=*.:=*++########-:::-+.....................................############*.+......-+..............+***************+++++*+++++*******%%#*
                .#++++++************************.......::######= +=+*..........................................##########**#*..:...........-*=-=#******************+++++++++*++++****%%***
                ..#+++**************************%.....######==+======..........................................##########*****=.=.............*****##*************++++++++++*+++#***%#****
                ...#+++***************************..####+:#*===++::::..:.................::...................%###*########******-#...................%%%%%%%%***++++++++++++++%***%******
                ....#++********#********************##=::..*=:::::-:*.........................................########*######********#:................#%%%%%%%%%%%@#+++++++*%%***********
                .....#+*********#*********************##*...+::::*...........................................#############****###*######*****#.........**%%%%%%%%%%%%%%%%%%#**************
                ......#**********#********************=.....................................................##############****************...........=+***#%%%%%**#%%%%#************####%%
                .......#**********##********************...................................................###############***********#**-..............*****%%%%%%******#%%%%%%%%%%%%%%%%%
                ........#**********###********************:....................................:..........################*********##**=..............:=******%%%%%%%%%%%%%%%%%%%%%%%%%%#*
                ........##***********##*******************#**.................................:.........:#################********#****...............::*********%%%%%%%%%%%%%%%%%%%%%%%%%
                .........##********#****#*******************#.+:...........................:...........*##################******##****:..............:::+**************#%%%%%%%%%+%%%%%%%%
                .........###**************#*******************..+:....................................::*################******#*****+...............:::++*****************#%%%%%+%%%%%%%%
                .........####**********************************+..+..................................#:::=################**********#:..............::::#++****************%%%%%*#%%%%%%%%
                .........:####***********************************..*...............................+*=##::-#############************:...............::::++++***************%%%%%+%%%%%%%%%
                ........::#####************************************.*.............................::#::-##:-########****************:...............:::::++++*************#%%%%%+%%%%%%%%%
                .....:::::#######*********************#*************-:-==.......................*:::-::::=##-######***************#:................:::::+++++************%%%%%%+%%%%%%%%%
                ..::::::::%#######************************##**********::::::::::-=+******##*=-:::::::+:::::+####+#****************:..................::::+++++++**********%%%%%**%%%%%%%%%
                ::::::::::=########**************************########***:::::::::::::::::::::::::::::-:::::::*###*-:*#****####***-....................::::+++++++*********%%%%%+%%%%%%%%%%
                ::::::.....##########****************************#########=:::::::::::::::::::::::::::#::::::::####-::::::--#***.......................:::*+++++++++******%%%%%+%%%%%%%%%%
                :::........%###########*******************************#######-:::::::::::::::::::::::::-:::::::::*##-:::::-***......................:::::::++++++++++++++***#%%+%%%%%%%%%%
                ...........-###-####**##***********************************#####:::::::::::::::::::::::::::::::::::=##=-***.....................-::::::::::=+++++++++++++******=%%*%%%%%%%
                ............###::**#####*#********************#*******************#+:::::::::::::::::::::-:::::::::::+#*-......................-:::--:::::::=++++++++++++******=%#**%%%%%%
                ........:::.:###+*****######********************###********************#*::::::::::::::::::::::::::::::=**...................:############%%%%%*+++++++++******-%****%%%%%
                ......:::....=###**********##*********************####*************************-::::::::::::::::::::::-::-**................:#############%%%%%%**+++++++******=*******%%%
                ..:::......... #*************************************####*************************#:::::::::::::::::--...::=**..............##############%%%%%%%%***++++******=********%%
                ............. ###+*************++#*********************####*************************#:::::-:::::::--.......::+**:..........:###############%%%%%%%%****++******+**********
                .............#####**************+++#**********************###*********************#***#::::--::--.............:**-.*......:%################%%%%%%%%***********=**********
                ............#######**************+++++#***********************#*#************#****##**##:::--:-................-:..+**....:##################%%%%%%%%**********-**********
                ......:::::########**************++++++++#***********************###***********::#######=:::-:::..:::...........:+*#****-::###################%%%%%%%%%********+**********
                ..:::::::=+#########**************+++++++++++#*********************####******#**:::-#####:::-:::::::................:*****###############%#####%%%%%%%%#********+*********
                .:::::::::*+#########*************++++++++++++++**********************###****#***=:::*###+:::::::::...................******%#############%%####%%%%%###%********%********
                :::::::::*%%++#########***********++++++++++++++++++#******************#*##*###*##*:::-###-:::::::....................*#******##########################%%%*******%*******
                ::::::::**%%%#+#########**********+++++++++++++++++++++#************##**#**#*#######:::%##:::::::......................*#*******#######################%%%%%******+%******
                :::::::*###%%%%++#########**+*****++++++++++++++++++++++++#******#**#**##############:::##::::::.......................**********######################%%%#%%%*****+%*****
                ::::::#####***%%#+##########*++***+++++++++++++++++++++++++++#*##**##################*::##::::..........................***********####################%####%%%%****+%****
                ::::-*********##*%+############++++++++++++++++++++++++++++++++*#*####################-+##..............................************#########################%%%%%***+%***
                ::****####*******#*++#############++++++++++++++++++++++++++++++++#####################%##..............................:*************########################%%%%%%%*+%**
                *******************#*+*############**++++++++++++++++++++++++++++++*#####################+...............................**************########################%%%%%%%%*%*
                *********************##+*###########****+++++++++++++++++++++++++++++####################................................***************######################%%%%%%%%%%%#
                **********************#*%++#########*******+++++++++++++++**+++++++++++###################...............................:***************####################%%%%#%%%%%%%%
                *************************###+++######*********++++++++++++++**++++++++++##################................................****************##################%%%%%##%%%%%%%
                ***********************#%#***%%%++####***********++++++++++++***+++++++++##################...............................****************#################%%%%%%####%%%%%
                ***************************#%**%%%%+++#**************+++++++++++**++++++++*################...............................#****************%########%#####%%%%%%%#####%%%%
                ******************************%*%%%%%%*+=#**************+++++++++++*++++++++################..............................=*****************##############%%%%%%######%%%%
                ******%****************####%%%#*#*%%%%%%%%==****************+++++++++*+++++++###############..............................:*****************#######%#####%%%%%%%#####%%%%%
                ******%%*******###%%%%%%%%%%%%%%%%%%%%%%%%%%%===****************+++++++*++++++##############*..............................*****************#####%%%####%%%%%%%#####%%%%%%
                ******%%%***%***#%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%*==#***************++++++++++++##############..............................******************##%%%%%###%%%%%%%%####%%%%%%%
                ******%%%%***********%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#==#****************+++*+++++######%######..............................******************%%%%%%###%%%%%%%#####%%%%%%%%
                ******%%%%**************%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%==+****************+++++++%###########-............................:*****************#%%%%%%##%%%%%%%####%%%%%%%%%%
                *******%%%%***************%%%%%%%%%%%%%%%%%%%%***%%%%%%%%%===*************#+++++++############............................+*****************%%%%%%###%%%%%#####%%%%%%%%%%%
                ********#%%%%%%%%%%%%%******#%%%%%%%%%%%%%%%%%%#***%%%%%**%%%*==***********%+**++++###########............................#*****************%%%%###%%%%%#####%%%%%%%%%%%%%
                %%%%%#****#%%%%%%%%%%%%%******%%%%%%%%%%%%%%%%%%#%***********%%%%==********%#***++++##########............................*****************%%#####%%%%###%%%%%%%%%%%%%%%%%
                %%%%%%%%%%**#%%%%%%%%%%%%******%%%%%%%%%%%%%%%%%##%********#********%=+****%%+***+++*#########............................****************%######%%%#%%%%%%%%%%%%%%%%%%%%%
                %%%%%%%%%%%%%##%%%%%%%%%%%******%%%%%%%%%%%%%%%%%#%#****#***************#-*#%%*****++########*...........................=***************%%%%%%%##%%%%%%%%%%%%%%%%%%%%%%%%
                %%%%%%%%%%%%%%%%%%%%%%%%%%%*****%%%%%%%%%%%%%%%%%%%%**#*******************%%%%%*****++#######............................*#*************%%%%##%%#####%%%%%%%%%%%%%%%%%%%%%
                %%%%%%%%%%%%%#%%%%*%%%%%%%%%****#%%%%%%%%%%%%%%%%%%%%**********************%#%%#*****++######............................*#***********%%%%%%%%%%%%%#######%%%%%%%%%%%%%%%%
                %%%%%###**********#%***%%%%%%****%%%%%%%%%%%%%%%%%%%#***********************#*%%*******#####............................###******##*%%%%%%%%%%%%%%%%%#####%#%%%%%%%%%%%%%%
                #*******************#%#***#%%%%**%%%%%%%%%%%%%%%%%%%*************************##%%*******####...........................-*#*******#%%%%%%%%%%%%%%%%%%%%%%#%#%%%%%%%%%%%%%%%
                *********************#%%%***%%%%%%%%%%%%%%%%%%%%%%%%***************************%+%*******##............................*##**##-...%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                **********************%%%%%**%%%%%%%%%%%%%%%%%%%%%%%****************************%+%**********=......................**##**.......=%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                ***********************%%%%%%*%%%%%%%%%%%%%%%%%%%%%%*****************************%#%%*********#**..............-#**#=............%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                Add me Blue Archive NA: AYZHEEOU
                """;

        // all credit goes to the original creator of this ascii art
        String art2 = """
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡌⠸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡅⢘⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠨⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠒⠚⠛⠋⡧⡎⠭⠭⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠔⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠌⡂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠂⡅⠀⠀⠀⠀⠀⠀⠀⠀⡠⠉⠀⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡅⡃⠀⠀⠀⠀⠀⠀⡀⠃⠀⡰⠂⠠⠀⠁⠂⢄⡇⡀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⡔⣒⡤⠄⠄⠒⠂⠄⡀⠀⡇⡇⠀⠀⠀⢀⠌⠀⠀⠀⡂⠀⠀⠀⠈⠂⢄⠀⠀⢃⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⢀⠂⡀⠁⠀⠈⠀⠀⠀⠀⣏⠈⠁⠉⠁⠐⢤⠃⠀⠀⠀⠘⠀⠀⠀⠀⠀⠀⠀⠹⠀⠀⡃⠀⠀⠀
                ⠀⠀⠀⠀⠀⢀⠇⢠⠂⠀⠀⠀⠀⢁⠀⠀⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠰⠀⠀⡂⠀⠀
                ⠀⠀⠀⠀⠄⠁⣀⠋⠀⠀⠀⠀⠀⠀⠤⠀⡁⠀⠀⠀⠀⠠⠠⠤⠀⠀⡀⠁⠂⠀⠀⠀⠀⠀⠀⢀⡏⠀⠀⠡⡀⠀
                ⢀⠀⡠⠎⠀⠀⡇⠀⠀⠀⠀⠀⠀⡊⠁⠑⠃⠀⠀⠄⠁⠀⠀⣆⠐⠀⠀⠀⡁⠀⠀⠀⠀⠀⠀⡃⠀⠀⠀⠀⠈⡆
                ⡬⡋⠀⠀⠀⡎⠀⠀⠀⠀⠀⢀⡊⠀⠀⠀⠀⠀⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠀⠀⠀⠀⠀⠀⠃⡀⠀⠀⡐⠁
                ⠀⠈⠃⢄⠀⡅⠀⠀⠀⠀⢠⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⡀⡓⣇⢀⢀⡀⡀⡀⡸⣀⠄⡇⠀
                ⠀⠀⠀⠀⠙⢆⠀⠀⠀⠀⡂⠀⠀⠀⠀⠀⠀⠀⢀⣀⡠⠄⠄⠂⠀⠁⠈⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠈⠋⠀⠀
                ⠀⠀⠀⠀⠀⢸⡀⡀⢀⠀⠠⠀⠠⠠⠤⠒⠒⠂⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            """;

        // all credit goes to the original creator of this ascii art
        String art3 = """
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⠀⠀⢸⡄⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⣤⣤⡤⣤⣀⣻⣷⣶⣾⣿⣦⡀⢠⠇⢀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢤⣤⣼⣿⣇⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⢴⣞⣳⢦⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡀⠀⠀⠀⠀⢾⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣄⠀⣯⡜⣷⢳⡀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡙⠃⠀⠀⣲⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣾⡿⣿⣿⣿⣷⡌⢷⠺⣧⣵⡀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣼⣿⣿⣿⣿⣿⡑⠀⠀⠀⠈⢲⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⣿⣟⢿⣷⣺⣿⣿⣿⡗⠌⠳⣿⣿⠇⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠋⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠣⠀⠀⠰⠲⣩⠃⠀⠀⠀⠀⠀⠀⠀⢀⣴⣾⣿⣿⠻⣿⡆⢹⣷⣝⣿⣿⣷⣧⠶⠊⠁⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⢰⢿⣿⣿⣿⣿⣿⣿⡟⠛⠛⠛⣿⢏⣽⣿⣿⡿⣿⣿⣿⡏⠀⠀⣀⡠⣤⣀⡔⡹⣀⠩⠶⣄⣠⣾⣿⡿⣿⡌⢻⣧⣈⣿⣿⣿⣿⣿⣟⣛⣢⣄⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣧⠀⠀⠀⠀⠀⠀⠀⠀⠸⣻⣿⢻⣿⣹⠛⠃⠀⠀⡾⠁⢸⠏⣹⣏⣱⡎⣼⣷⣅⣴⣿⢟⣷⢋⠜⣰⢃⣿⣱⣾⣿⣿⣟⢿⣷⡘⣿⣦⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣹⢿⡸⣖⠿⡿⠿⣶⣏⠁⠶⣿⠿⠿⢋⡽⣧⣻⣿⣷⡽⠛⠝⢁⡌⠰⠃⠺⣿⣿⠿⣧⡘⣿⣤⣻⣿⣾⣿⡿⢋⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣾⣿⣿⣷⣧⠈⠁⠒⠈⠋⠁⣀⠀⠈⠉⠉⠁⣼⣽⠟⠛⢫⠀⠸⡔⢎⠘⢦⡀⠐⠹⢿⣦⡸⣷⣾⣿⣿⠟⢋⢛⡑⠛⢯⣿⣟⡿⢿⣿⣿⣿⣿⣿⣿⡽⣯⠧⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣤⣤⣶⣿⣿⣿⣿⣿⣿⣿⣦⣤⡀⠀⠀⠀⣿⠀⠀⠀⠀⢀⣿⣿⡀⠀⠺⣦⡀⠙⢦⡛⠄⡹⠦⣤⡜⢻⣿⣿⠿⢋⠀⡀⠂⣴⢎⠰⣀⠍⠿⢿⣿⣿⣿⣿⣿⣿⣿⣷⣻⣧⢣⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⢾⣵⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⡇⠀⠶⠾⠶⠶⠶⠄⢀⣾⣿⣿⣿⡖⣤⡬⢿⣶⣄⣈⣻⣿⣐⡜⠛⣿⠛⡥⢋⢄⡈⢐⣰⠯⣤⣃⢄⠫⣹⢚⣜⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⡇
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡌⣹⣷⣄⠀⠉⠀⣀⣴⣿⢸⣿⣯⣿⣧⣼⡷⠀⢻⣿⣿⣿⣶⡶⠟⣍⢣⠙⠂⠃⠀⢀⣉⠛⠓⠲⠬⢄⢣⢎⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢷⠇
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⢟⣿⣿⣿⣿⣿⣿⣿⣿⣟⣿⣿⣿⣿⡃⣿⣿⣛⣶⠤⠴⣿⣿⣿⡸⣿⣿⣿⣿⣿⠇⢴⣈⣿⠛⣛⠻⡇⠴⠉⠊⡀⠔⠂⣠⣾⣿⣤⣀⠀⠀⣈⣞⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢋⠎⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡿⢿⣿⣿⣿⣿⣿⢿⣿⠋⠉⠛⠸⣿⣿⣿⣿⡧⢠⠟⠙⣺⠏⣹⠟⠉⢐⠹⣿⣿⣿⣻⣦⣀⣙⠉⠈⠀⠄⠩⠄⠀⣀⣠⣴⣾⣽⣿⣿⣬⣟⠛⣟⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢏⣵⡏⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣷⣬⡲⣄⠀⠀⣿⣿⣿⣿⡇⠐⢾⡶⡯⠛⠁⣠⣶⣿⣷⡙⣿⣿⣿⢯⣿⣿⣿⣿⡲⣤⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣍⣛⠻⢿⢿⣿⣿⣿⣿⣿⠿⣋⣴⣿⡿⠃⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⡿⢿⣟⣿⣿⣿⣿⣮⣧⠀⣿⣿⣿⣿⡇⢀⠤⠊⢀⣴⣾⣿⣿⣿⣿⣿⣾⣟⣛⣿⣿⢻⣿⡿⠇⢻⣷⣾⣿⣿⣿⡿⠿⠟⠛⠛⠿⠿⢿⡿⣿⣿⣿⣾⣿⣿⣯⣷⣾⡿⠟⠋⠁⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢘⣿⣿⠿⢿⣿⣿⣿⡎⢿⣸⣿⣿⣿⣿⠟⢑⣾⣿⣿⡾⠘⠁⣠⣶⠟⠋⠀⢀⣡⠟⣥⣾⣿⣿⠀⣿⣷⠀⣿⣇⢰⡌⢿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⣠⢶⠶⣿⣿⣿⠰⣮⣿⣿⣿⣿⣄⠁⣿⣿⣿⣎⡀⠛⢻⠸⠋⣀⣤⡾⠟⠁⠀⢀⡴⢋⠬⡹⣴⣿⣿⡿⢠⣿⣯⠀⢺⣿⡘⣷⡍⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⢐⣷⡘⢿⣷⣾⣤⣄⡉⣙⣿⣿⣿⣿⣧⣹⣿⣿⢿⣿⡴⠊⣀⣴⡿⢋⠄⠀⡠⢾⡻⢄⣵⡾⣵⣿⣿⣿⡷⢸⣿⣷⠀⠘⣿⣿⣿⣿⣽⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⢀⣴⣿⠟⣃⣤⣾⣿⣿⣿⣿⣿⣻⡿⠛⠉⠀⢀⠈⡹⠒⠋⣀⣾⠟⠁⠐⣁⠔⠁⢸⣼⡀⣿⣵⣿⣿⣿⣿⣿⡷⢸⣿⣿⢸⣇⣿⣿⣿⣽⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⣿⣿⣿⣿⢿⣿⣿⣿⣿⡿⠿⠛⠁⠀⠀⠀⢦⣿⢸⣅⣴⡾⠋⠁⢀⣠⣾⣿⡄⠀⢨⢹⣶⣿⣿⣿⣿⣿⣿⣿⡧⢸⣿⣿⢸⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⢸⣿⡿⢋⣶⣿⣿⣿⣿⠏⠀⠀⠀⠤⣄⠀⠀⠀⢿⡈⢿⣿⣂⣠⣴⣿⣿⣿⣯⣴⣴⣿⢿⠟⢯⣿⣿⣿⣿⣿⣿⣷⣾⣿⣷⢨⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⣿⠟⣵⣿⣿⣿⣿⣿⠸⡆⠀⠀⣄⡀⠙⢷⣄⠀⠈⢷⣌⡻⣯⣽⣿⣿⣿⣿⣿⣿⡿⣛⢸⣾⣿⣿⣿⣿⣿⣿⣿⣟⣿⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⡝⣼⣿⣿⣿⣿⣿⣿⣧⣿⣖⠠⣌⡛⢦⣀⠹⣦⠀⢀⠍⠉⢰⣿⣿⣿⣿⣿⣿⣿⣿⣽⣾⣿⣿⣿⣿⣿⣿⣿⣿⢁⣿⣿⣿⣿⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⢰⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣭⣝⣻⢯⣗⡾⣿⣿⣾⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⣼⣿⣿⣿⣿⣿⣿⡧⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠈⢾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⣿⣿⣿⣿⣿⣿⣿⣏⣼⣿⣿⣿⣿⣿⣿⣿⣷⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠑⠈⠙⢾⣋⣿⣿⣿⣿⣿⣿⣿⠟⢩⣿⣿⣿⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣼⣿⣿⣿⣿⣿⣿⣿⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⢠⣼⣿⣿⣿⣿⣿⠟⠁⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⠟⠉⠀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⡿⠉⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢯⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⢀⣴⣿⣿⣿⣿⡿⠋⠀⠀⠀⠀⣼⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣏⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⣠⣿⣿⣿⣿⡿⠋⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⢀⣴⣿⣿⣿⣿⠟⠁⠀⠀⠀⠀⠀⢠⣿⣿⢿⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                """;

        String art4 = """
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⡒⢄⡀⠀⠀⠀⠀⡴⣏⡝⣳⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠒⠿⠀⠀⣠⡾⠛⠉⠉⠉⠛⠷⣄⠀⠀⠀⣤⠖⢻⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⡾⠋⠀⠀⠀⠀⠀⠀⠀⠀⠙⠢⣀⠈⠓⠊⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⡴⠾⢝⡷⠁⠀⠀⢠⡤⣤⢄⣀⣀⣀⠀⠀⠈⠳⡦⣀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡤⠔⠒⠊⠉⠉⠉⠉⠉⠉⠉⠙⠓⠲⠤⢤⣈⣷⡱⢎⡳⣍⢎⡟⣆⠀⠀⠈⢯⡛⢶⣤⣀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡤⠒⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠓⠷⢼⣚⣜⣺⣀⣠⡠⠤⠿⠖⠚⠚
                ⠀⠀⠀⠀⠀⠀⠀⣤⢶⡶⣶⣶⠋⢀⣤⠶⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣩⠟⠉⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⣾⣛⢧⢞⣽⢃⠔⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣞⠁⢀⣀⣀⡀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⢠⢿⣜⣭⣾⣗⡋⣀⣤⣴⣶⢶⢲⣛⠿⣹⢏⣟⡻⣖⢶⣲⢤⣄⣀⠀⠀⢢⡀⢲⠒⠉⠉⢿⣿⣿⢽⢺⣱⢻⣦⠀⠀⠀⠀
                ⠀⠀⠀⠀⢠⠞⠉⣀⣀⡟⣷⠉⠉⣿⠇⠆⣬⡷⣭⣋⠅⢎⠤⢩⢉⣷⠙⣮⢓⠻⣿⣶⣄⠽⢶⣷⣲⡷⠶⣝⣮⢏⡳⣎⢷⢻⡆⠀⠀⠀
                ⠀⠀⠀⠀⢙⡿⣟⣽⠀⣿⢼⣦⣾⣛⣬⡱⣼⡟⣦⣹⣌⢢⣑⢃⠆⣾⣩⢟⣇⣊⣽⣧⠀⠀⣼⣿⢻⢃⣀⣀⠹⣿⣱⢫⣞⢯⣿⠀⠀⠀
                ⠀⠀⠀⠀⢸⡷⣟⣾⠀⢸⢞⣿⣿⡜⡶⣹⢮⣿⢶⣹⣏⢧⢏⣏⢻⣧⢏⣿⢲⡳⣼⢻⣁⣼⣿⠯⣟⡧⠀⢻⣽⣿⣽⣗⢮⠞⣿⠀⠀⠀
                ⠀⠀⠀⠀⢸⣗⡯⢾⡄⢻⣯⢾⣏⢾⡱⣣⡟⢺⣳⠟⣿⣾⣚⡬⣏⣿⠾⣿⣷⢽⠲⣏⣿⠿⣍⣾⣿⡇⠀⣸⣿⡞⢶⡹⣎⡟⣼⡄⠀⠀
                ⠀⠀⠀⠀⣼⣣⢟⡞⣧⣸⣿⣾⣏⣶⣽⠟⠁⠀⢻⡟⣼⡿⣧⡳⣭⢞⣿⡵⣺⣯⣻⡜⣽⣿⣸⣿⣿⡇⢀⣿⣿⣝⡣⣗⣣⡝⡞⡇⠀⠀
                ⠀⠀⠀⣰⠿⣬⠳⣎⣳⣽⢻⣻⣿⣿⣁⣀⢀⠀⠀⠉⢉⠉⠈⠉⠓⠛⣃⣉⣉⠉⠉⠓⣷⣾⠿⣿⣿⣥⣿⣼⣿⣎⡗⢮⡱⣝⡺⡇⠀⠀
                ⠀⢀⡴⣏⢟⡞⡧⢧⣟⢭⣓⢮⣽⣿⡜⡏⠙⠛⢻⡟⠛⠀⠀⠀⠀⠀⠛⠛⠻⣶⠶⠶⣾⣏⠷⣣⣿⣿⣿⡿⣿⡞⣼⢣⡛⢶⣙⣧⠀⠀
                ⠒⠿⠽⠚⠉⡿⢾⣝⣌⣧⣾⢞⣼⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⢭⣛⣵⡇⠈⢻⠀⣿⡟⢦⢣⡝⢦⢣⢻⠀⠀
                ⠀⠀⠀⠀⠀⡧⢇⡎⢾⣿⣿⢎⣿⣟⡳⠀⠙⠒⠒⠁⠀⠀⠀⠀⠀⠀⠀⠢⠤⠠⠤⣾⢫⢖⡱⣾⠁⠀⣸⠀⣿⣋⠎⡵⢈⠇⡅⣻⠀⠀
                ⠀⠀⠀⠀⢸⡇⡖⢈⠆⢿⣿⡜⣼⣿⣷⣤⣀⠀⠀⠀⠀⠠⠴⠒⣤⠀⠀⠀⠀⠠⠾⣾⡡⢎⣱⣏⡀⠔⠃⠀⣿⠜⡒⠄⠣⠌⠄⡹⡄⠀
                ⠀⠀⠀⠀⣼⢠⡁⢂⠊⠼⣿⢷⠸⣿⣟⣿⣯⡙⠒⠤⠤⢀⣀⣀⡀⣀⡠⠤⠤⠒⢪⡟⣐⣳⣿⡁⠀⠀⠀⣼⠛⣰⢊⠈⡂⠐⡀⢧⡬⠶
                ⠀⠀⠀⢠⡇⡼⢀⠂⠀⠆⣿⢼⡅⣿⣯⣿⣿⣷⣤⡀⠀⣠⣺⣿⣿⣞⠟⣦⡀⠀⣾⣷⡼⣷⣿⠅⠀⢀⡞⢁⡶⠋⡀⠔⠠⢱⡌⢸⡄⠀
                ⠀⠀⠀⣼⢠⡇⡀⠀⢁⠂⡟⡇⣿⣿⣳⠧⢹⠀⠀⢀⣾⡟⢿⣟⣿⠟⠖⣿⢱⣠⡇⢻⣿⣽⣿⠀⠺⠷⠒⢫⡇⠂⡁⠀⡁⣸⡇⠈⣇⠀
                ⠀⠀⢠⡇⣸⡇⠄⠡⠀⢸⠇⢧⠸⡽⠋⠀⣻⠀⢠⣞⡿⠀⡎⢀⡇⠀⠐⣿⣏⣿⡅⢈⢿⡿⠃⠀⠀⠀⠀⢸⡀⢁⠠⠁⠄⣽⣷⠀⢻⠀
                ⠀⠀⢸⠁⡏⡇⠠⠁⠂⣼⠀⣸⠞⠀⡐⢁⡏⢀⣿⣟⠇⢰⡡⠝⠃⠀⠀⣟⣾⡞⣇⠂⠌⢧⠀⠀⠀⠀⠀⣼⠀⠆⡀⠘⣰⣿⢽⠀⢸⡄
                ⠀⠀⠸⠦⠇⠧⠤⠤⠥⠇⠰⠥⠤⠤⠤⠼⢁⣮⣿⣿⣀⣀⣀⣀⣀⣀⣠⣿⣷⣯⡿⠦⠤⠬⠷⠄⠀⠀⡰⠧⠴⠤⠤⠵⠿⠝⠬⠬⠼⠁
                        Man there are almost no iconic maces huh
                """;

        String art5 = """
                ⣿⣿⣿⣿⡟⣼⣤⢍⣾⣿⣿⣿⣿⣿⠿⣛⠭⣺⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡲⣝⢿⣿⣿⣿⣿⠠⣿⡏⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⡵⣿⡟⣼⣿⣿⣿⣿⡿⣣⢊⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢸⢾⣿⣿⣿⠋⣰⡟⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣆⢿⡀⠹⣿⣿⣿⣿⡇⡇⢿⣿⣿⣿⣿⣿⣿⠿⠿⣿⣿⣿⡿⠿⢿⣿⣾⣾⠿⠋⢐⡵⢋⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣮⡳⣄⠘⠻⢿⣿⣿⣌⡢⢝⣛⠟⣫⣵⣶⣿⣿⣿⣿⣿⣿⣿⣷⣶⣮⣥⣈⠞⢫⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⣮⣝⠢⢤⣈⠉⠭⢛⡅⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣍⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡭⢩⣾⢇⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣌⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⣰⣿⠏⣼⣿⣿⣿⣿⣿⣿⡟⣿⣿⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⡹⢘⢿⣿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⣰⣿⠏⣼⣿⣿⣿⣿⣿⣿⣿⢟⣿⡇⢸⣿⣿⣿⣿⣿⣿⣿⣿⡟⣿⣿⣿⣿⣷⡹⣎⣿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⣴⣿⠏⣼⣿⣿⣿⡟⣿⣿⣿⣿⢸⣿⢠⣼⣿⡿⣿⣿⣿⣿⣿⣿⢧⢹⣿⣿⣿⣿⣷⡍⢻⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⣿⡏⣼⡘⠛⣸⣿⣿⣿⣿⡇⣿⣿⡿⠿⠸⠇⠷⢸⣿⢹⢻⢹⣿⣿⣿⣿⢸⢸⣿⣿⣿⣿⣿⣷⠹⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⢏⣲⡿⠰⢰⣿⣿⣿⡻⣿⣷⢩⣵⣶⣿⠘⣿⣿⡀⣿⢸⢸⢸⣿⣿⠿⠇⠸⠈⣿⣿⣿⣿⡿⢿⢇⢻⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⢈⢿⡇⠇⣾⣿⣿⢋⢁⢿⣟⠆⠿⠟⠻⠓⣛⣿⣧⠻⡄⣄⡆⢿⣿⡿⣱⡵⡄⠳⣿⣿⣿⣿⢸⣼⡘⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⡏⣶⢬⣿⠀⡿⣿⣿⡊⡄⢎⠟⢁⠄⣶⣦⢱⣄⣽⣿⣿⣷⣦⣤⣸⠋⠤⢙⣳⡇⣿⡌⣿⣿⡿⢸⡷⡇⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⡐⣛⣮⣿⣆⡙⠘⡟⣷⡐⣱⣆⢻⠹⣯⡿⠘⣿⣿⣿⣿⣿⣿⣿⡖⢸⣷⢠⡈⢁⣼⢃⣿⡟⡄⣼⠃⡇⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣧⠹⣿⣧⡻⣿⣗⠮⣊⡳⣄⢹⣿⣷⣾⣷⣾⣿⣿⣿⣿⣿⣿⣿⡀⢤⠟⢸⡇⠸⢧⣾⠟⡜⣰⡿⢸⢇⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⣧⠙⠿⣿⡇⣿⢦⠳⣝⠚⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣿⣿⡟⣰⣭⣮⡶⡣⣁⣎⣾⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⡿⣾⣦⡹⠗⣿⣻⣿⣿⣇⢷⣾⣿⣿⣿⣿⡿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⣼⣿⣿⣿⣿⢺⣿⣿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⡇⣻⣼⣿⡆⡇⣿⣿⣿⡜⣌⢻⣿⣿⣿⣿⣿⣿⣶⣶⣶⣾⣿⣿⣿⣿⡟⣸⣻⠝⠿⢿⣯⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⡇⣿⡿⢿⣇⡇⣿⣿⣿⣷⠹⣷⡈⡻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⣂⣽⣿⣿⣿⣇⣿⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⠁⣿⢹⢺⡿⡇⢹⣿⣿⣿⣧⢹⣇⣿⣦⣝⡻⢿⣿⣿⠿⢛⣫⣵⣾⣿⣟⣿⣿⣿⣿⣿⢸⡊⣿⣿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⢸⣿⢸⢸⡇⣧⢸⣿⣿⣿⣿⡌⣘⡛⠿⣿⣿⣷⣶⣖⣼⣿⣿⣿⣿⣿⣿⠘⣿⣿⡿⣿⣇⢇⢻⣿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⡏⣼⡇⣾⠸⠇⣿⢘⣿⣿⣿⣿⣧⢹⣿⣿⣶⣮⣭⡟⠛⠈⠍⣿⣿⣿⣿⣟⡂⢿⢻⣷⢹⣿⣜⡜⣿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⠇⣿⣧⣿⠘⠸⢟⣠⢻⣿⣿⣿⣿⡇⠻⢿⣿⣿⣿⠣⠷⢟⡈⠘⠿⠿⣿⣿⣿⡜⣇⢻⣇⢻⣿⣧⢻⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⡿⢸⠿⢛⣭⣶⣬⣛⡛⡘⣿⣿⣿⣿⣿⡌⣦⡙⠉⢁⣶⣌⠻⣿⣜⣀⠁⡂⠭⢛⠷⢹⣆⠻⡌⢿⣿⡎⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⡟⢰⣶⣷⣮⣝⠿⣿⣿⣿⣁⢻⣿⣿⣿⣿⣷⣈⠉⣢⡙⠿⣿⣷⣮⠡⡱⣡⣌⠳⣄⢉⠢⢿⡍⡻⡌⣿⣿⡜⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣇⢸⣿⣿⣿⣿⣿⣮⡻⣿⣿⡆⣿⣿⣿⣿⣿⣧⠸⣿⣿⣷⣌⠻⣯⣵⣜⠮⠻⢷⡌⢮⡂⡚⢷⡱⡜⡜⣿⣿⡘⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⢸⣿⣿⣿⣿⣿⣿⣿⣦⢻⣿⢸⣿⣿⣿⣿⣯⢣⠹⣿⣿⣿⣷⣬⡹⢿⣦⢻⣮⡻⣦⡙⣦⣄⠓⡘⠜⡜⣿⣷⡹⣿⣿⣿⣿
                ⣭⣟⡻⠿⠿⠘⣿⣿⣿⣿⣿⣿⣿⣿⢀⢿⣇⢿⣿⣿⣿⣿⣇⢥⣙⡛⠿⢿⣿⣿⣦⡙⢧⡹⣿⣎⠳⣌⢻⣷⣌⠲⠀⡜⣿⣧⢻⣿⣿⣿
                ⡙⠻⣿⣿⣶⣥⡚⢿⣿⣿⣿⣿⣿⣿⡘⡜⣿⡜⡟⣿⣿⣿⣿⡜⣿⣿⣿⣶⣬⣝⡛⠿⣦⣑⠼⣿⣷⡙⡄⣿⣿⡷⢰⣌⡈⢿⣧⢻⣿⣿
                ⣿⣦⣄⠉⠻⣿⣿⣦⡙⢿⣿⣿⣿⣿⣇⢧⢹⡷⢹⡘⣿⣿⣿⣷⢹⣿⣿⣿⣿⣿⣿⣷⣦⣝⠓⢉⢵⣌⠃⣿⣿⡇⣿⣿⣿⣎⠻⣧⢻⣿
                ⣛⠿⢿⣷⣦⠈⠛⢿⣿⣦⠙⠻⣿⣿⣿⠸⡆⣴⣬⣧⢹⣿⣿⣿⣦⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡉⠻⣿⣿⣿⢡⣿⣿⣿⣿⣧⡹⣯⠟
                """;

        String art6 = """
                """;
        if (finalWeaponName.equalsIgnoreCase("Red Dragon") && weaponName.equals("Gun"))
        {
            System.out.println();
            System.out.println(art);
            System.out.println();
        }

        if (finalWeaponName.equalsIgnoreCase("Excalibur") && weaponName.equals("Sword"))
        {
            System.out.println();
            System.out.println(art2);
            System.out.println();
        }

        if (finalWeaponName.equalsIgnoreCase("Enten") && weaponName.equals("Katana"))
        {
            System.out.println();
            System.out.println(art3);
            System.out.println();
        }

        if (finalWeaponName.equalsIgnoreCase("Morning Star") && weaponName.equals("Mace"))
        {
            System.out.println();
            System.out.println(art4);
            System.out.println();
        }

        if (finalWeaponName.equalsIgnoreCase("Secretary's Vote") && weaponName.equals("Gun"))
        {
            System.out.println();
            System.out.println(art5);
            System.out.println();
        }

    }

}
