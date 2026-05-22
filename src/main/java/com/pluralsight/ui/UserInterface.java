package com.pluralsight.ui;

import java.util.Scanner;

public class UserInterface
{
    private static Scanner userInput = new Scanner(System.in);


    public static void homeScreen()
    {
        System.out.println();
        System.out.println("Weapon Shop");
        System.out.println("------------------------------------");
        System.out.println("1) New Weapon Order");
        System.out.println("0) Exit");
        System.out.println();

        while (true)
        {
            String choice = getUserInput("Select an option: ");

            switch (choice)
            {
                case "1":
                    orderScreen();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }
    }


    public static void orderScreen()
    {
        System.out.println();
        System.out.println("Order Menu");
        System.out.println("----------------------------------");
        System.out.println("1) Add New Weapon");
        System.out.println("2) Add Potion");
        System.out.println("3) Add Item");
        System.out.println("4) Checkout");
        System.out.println("0) Cancel Order");
        System.out.println();

        while (true)
        {
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
                    // insert CHECKOUT menu here
                    break;
                case "0":
                    homeScreen();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }
    }



    public static void weaponSelectionScreen()
    {
        System.out.println();
        System.out.println("Weapon Order");
        System.out.println("----------------------------------");
        System.out.println("Please select a Weapon Type");
        System.out.println("1) Piercing Weapon (Base Price: $300)");
        System.out.println("2) Blunt Weapon (Base Price: $250)");
        System.out.println("3) Ranged Weapon (Base Price: $500)");
        System.out.println("0) Go Back");
        System.out.println();

        while (true)
        {
            String selection = getUserInput("Select an option: ");
            switch (selection)
            {
                case "1":
                    System.out.println();
                    System.out.println("Please select your weapon");
                    System.out.println("A) Sword ($100)");
                    System.out.println("B) Katana ($200)");
                    System.out.println("C) Spear ($300)");
                    System.out.println("0) Go Back");

                    String choice = getUserInput("Select an option: ");
                    switch (choice)
                    {
                        case "A":
                            break;
                        case "B":
                            break;
                        case "C":
                            break;
                        case "0":
                            weaponSelectionScreen();
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            System.out.println();
                    }

                case "2":
                    System.out.println();
                    System.out.println("Please select your weapon");
                    System.out.println("A) Staff ($50)");
                    System.out.println("B) Mace ($150)");
                    System.out.println("C) Hammer ($225)");
                    System.out.println("0) Go Back");

                    String choice2 = getUserInput("Select an option: ");
                    switch (choice2)
                    {
                        case "A":
                            break;
                        case "B":
                            break;
                        case "C":
                            break;
                        case "0":
                            weaponSelectionScreen();
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            System.out.println();
                    }

                case "3":
                    System.out.println();
                    System.out.println("Please select your weapon");
                    System.out.println("A) Bow ($175)");
                    System.out.println("B) Gun ($250)");
                    System.out.println("C) Boomerang ($50)");
                    System.out.println("0) Go Back");

                    String choice3 = getUserInput("Select an option: ");
                    switch (choice3)
                    {
                        case "A":
                            break;
                        case "B":
                            break;
                        case "C":
                            break;
                        case "0":
                            weaponSelectionScreen();
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            System.out.println();
                    }

                case "0":
                    orderScreen();
                    break;

            }
        }
    }

    public static void potionSelectionScreen()
    {
        System.out.println();
    }

    public static void itemSelectionScreen()
    {
        System.out.println();
    }




    public static String getUserInput(String message)
    {
        System.out.print(message);
        return userInput.nextLine().trim().toUpperCase();
    }

}
