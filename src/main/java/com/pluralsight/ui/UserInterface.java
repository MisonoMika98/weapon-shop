package com.pluralsight.ui;

import com.pluralsight.models.*;
import com.pluralsight.util.ReceiptWriter;

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
                    System.out.println();
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
                    checkoutScreen();
                    break;

                case "0":
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
        System.out.println("Weapon Order");
        System.out.println("----------------------------------");
        System.out.println("Please select a Weapon Type");
        System.out.println("1) Piercing Weapon (Base Price: $300)");
        System.out.println("2) Blunt Weapon (Base Price: $250)");
        System.out.println("3) Ranged Weapon (Base Price: $500)");
        System.out.println("0) Go Back");
        System.out.println();


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
                            break;

                        default:
                            System.out.println();
                            System.out.println("Invalid option. Please try again.");
                            System.out.println();
                    }
                    break;

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
                            break;

                        default:
                            System.out.println();
                            System.out.println("Invalid option. Please try again.");
                            System.out.println();
                    }
                    break;

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
                            break;

                        default:
                            System.out.println();
                            System.out.println("Invalid option. Please try again.");
                            System.out.println();
                    }
                    break;

                case "0":
                    orderScreen();
                    break;

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
        System.out.println("Please select an Enhancement category");
        System.out.println("1) Regular Enhancement (Prices vary from $100-$300)");
        System.out.println("2) Premium Enhancement (Prices vary from $500-$10000)");
        System.out.println("0) Go Back");


            // this fixes the bug I had that made color variable break after I added the Enhancement object
            String color = null;

            String choice = getUserInput("Select an option: ");

            switch (choice)
            {
                case "1":
                    System.out.println();
                    System.out.println("Regular Enhancement List");
                    System.out.println("---------------------------------------------");
                    System.out.println("A) Fire ($100)");
                    System.out.println("B) Water ($100)");
                    System.out.println("C) Electricity ($100)");
                    System.out.println("D) Wind ($200)");
                    System.out.println("E) Ice ($200)");
                    System.out.println("F) Earth ($200)");
                    System.out.println("G) Wood ($300)");
                    System.out.println("H) Poison ($75)");
                    System.out.println("I) Sleep ($250)");
                    System.out.println("J) Drain ($250)");
                    System.out.println("0) Go Back");

                    String enhancementSelection = getUserInput("Select an Enhancement here: ");


                    switch (enhancementSelection)
                    {
                        case "A":
                            color = colorSelectionScreen();
                            Enhancement enhancement = new Enhancement("Fire", 100);
                            Weapon weapon = new Weapon(weaponName, weaponType, color, price, enhancement);
                            order.addValuable(weapon);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
                            orderScreen();
                            return;

                        case "B":
                            color = colorSelectionScreen();
                            Enhancement enhancement2 = new Enhancement("Water", 100);
                            Weapon weapon2 = new Weapon(weaponName, weaponType, color, price, enhancement2);
                            order.addValuable(weapon2);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
                            orderScreen();
                            return;

                        case "C":
                            color = colorSelectionScreen();
                            Enhancement enhancement3 = new Enhancement("Electricity", 100);
                            Weapon weapon3 = new Weapon(weaponName, weaponType, color, price, enhancement3);
                            order.addValuable(weapon3);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
                            orderScreen();
                            return;

                        case "D":
                            color = colorSelectionScreen();
                            Enhancement enhancement4 = new Enhancement("Wind", 200);
                            Weapon weapon4 = new Weapon(weaponName, weaponType, color, price, enhancement4);
                            order.addValuable(weapon4);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
                            orderScreen();
                            return;

                        case "E":
                            color = colorSelectionScreen();
                            Enhancement enhancement5 = new Enhancement("Ice", 200);
                            Weapon weapon5 = new Weapon(weaponName, weaponType, color, price, enhancement5);
                            order.addValuable(weapon5);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) +  " has been added to your order");
                            orderScreen();
                            return;

                        case "F":
                            color = colorSelectionScreen();
                            Enhancement enhancement6 = new Enhancement("Earth", 200);
                            Weapon weapon6 = new Weapon(weaponName, weaponType, color, price, enhancement6);
                            order.addValuable(weapon6);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
                            orderScreen();
                            return;

                        case "G":
                            color = colorSelectionScreen();
                            Enhancement enhancement7 = new Enhancement("Wood", 300);
                            Weapon weapon7 = new Weapon(weaponName, weaponType, color, price, enhancement7);
                            order.addValuable(weapon7);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
                            orderScreen();
                            return;

                        case "H":
                            color = colorSelectionScreen();
                            Enhancement enhancement8 = new Enhancement("Poison", 75);
                            Weapon weapon8 = new Weapon(weaponName, weaponType, color, price, enhancement8);
                            order.addValuable(weapon8);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
                            orderScreen();
                            return;

                        case "I":
                            color = colorSelectionScreen();
                            Enhancement enhancement9 = new Enhancement("Sleep", 250);
                            Weapon weapon9 = new Weapon(weaponName, weaponType, color, price, enhancement9);
                            order.addValuable(weapon9);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
                            orderScreen();
                            return;

                        case "J":
                            color = colorSelectionScreen();
                            Enhancement enhancement10 = new Enhancement("Drain", 250);
                            Weapon weapon10 = new Weapon(weaponName, weaponType, color, price, enhancement10);
                            order.addValuable(weapon10);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
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
                    System.out.println("Premium Enhancement List");
                    System.out.println("---------------------------------------------");
                    System.out.println("A) Dark ($500)");
                    System.out.println("B) Light ($500)");
                    System.out.println("C) Silence ($700)");
                    System.out.println("D) Armor Break ($1000)");
                    System.out.println("E) Psychic ($1500)");
                    System.out.println("F) Nuclear ($1500)");
                    System.out.println("G) Crystal ($3000)");
                    System.out.println("H) Time ($5000)");
                    System.out.println("I) Rune ($10000)");
                    System.out.println("J) Astral ($10000)");
                    System.out.println("0) Go Back");

                    String enhancementSelection2 = getUserInput("Select an Enhancement here: ");

                    switch (enhancementSelection2)
                    {
                        case "A":
                            color = colorSelectionScreen();
                            Enhancement enhancement = new Enhancement("Dark", 500);
                            Weapon weapon = new Weapon(weaponName, weaponType, color, price, enhancement);
                            order.addValuable(weapon);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
                            orderScreen();
                            return;

                        case "B":
                            color = colorSelectionScreen();
                            Enhancement enhancement2 = new Enhancement("Light", 500);
                            Weapon weapon2 = new Weapon(weaponName, weaponType, color, price, enhancement2);
                            order.addValuable(weapon2);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
                            orderScreen();
                            return;

                        case "C":
                            color = colorSelectionScreen();
                            Enhancement enhancement3 = new Enhancement("Silence", 700);
                            Weapon weapon3 = new Weapon(weaponName, weaponType, color, price, enhancement3);
                            order.addValuable(weapon3);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
                            orderScreen();
                            return;

                        case "D":
                            color = colorSelectionScreen();
                            Enhancement enhancement4 = new Enhancement("Armor Break", 1000);
                            Weapon weapon4 = new Weapon(weaponName, weaponType, color, price, enhancement4);
                            order.addValuable(weapon4);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
                            orderScreen();
                            return;

                        case "E":
                            color = colorSelectionScreen();
                            Enhancement enhancement5 = new Enhancement("Psychic", 1500);
                            Weapon weapon5 = new Weapon(weaponName, weaponType, color, price, enhancement5);
                            order.addValuable(weapon5);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
                            orderScreen();
                            return;

                        case "F":
                            color = colorSelectionScreen();
                            Enhancement enhancement6 = new Enhancement("Nuclear", 1500);
                            Weapon weapon6 = new Weapon(weaponName, weaponType, color, price, enhancement6);
                            order.addValuable(weapon6);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
                            orderScreen();
                            return;

                        case "G":
                            color = colorSelectionScreen();
                            Enhancement enhancement7 = new Enhancement("Crystal", 3000);
                            Weapon weapon7 = new Weapon(weaponName, weaponType, color, price, enhancement7);
                            order.addValuable(weapon7);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
                            orderScreen();
                            return;

                        case "H":
                            color = colorSelectionScreen();
                            Enhancement enhancement8 = new Enhancement("Time", 5000);
                            Weapon weapon8 = new Weapon(weaponName, weaponType, color, price, enhancement8);
                            order.addValuable(weapon8);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
                            orderScreen();
                            return;

                        case "I":
                            color = colorSelectionScreen();
                            Enhancement enhancement9 = new Enhancement("Rune", 10000);
                            Weapon weapon9 = new Weapon(weaponName, weaponType, color, price, enhancement9);
                            order.addValuable(weapon9);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
                            orderScreen();
                            return;

                        case "J":
                            color = colorSelectionScreen();
                            Enhancement enhancement10 = new Enhancement("Astral", 10000);
                            Weapon weapon10 = new Weapon(weaponName, weaponType, color, price, enhancement10);
                            order.addValuable(weapon10);
                            System.out.println();
                            System.out.println(colorize(weaponName, color) + " has been added to your order");
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
                    weaponSelectionScreen();
                    break;

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }
    }



    public static String colorSelectionScreen()
    {
        // uses the final color strings at the top of the class, RESET makes it so they don't bleed into the rest of the terminal's text
        System.out.println();
        System.out.println("Please select a color for your weapon");
        System.out.println("1) " + RED + "Red" + RESET);
        System.out.println("2) " + BLUE + "Blue" + RESET);
        System.out.println("3) " + GREEN + "Green" + RESET);
        System.out.println("4) " + YELLOW + "Yellow" + RESET);
        System.out.println("5) " + PURPLE + "Purple" + RESET);
        System.out.println("6) " + CYAN + "Cyan" + RESET);
        System.out.println("7) " + BLACK + "Black" + RESET);

        while (true)
        {
            String choice = getUserInput("Select an option: ");

            switch (choice)
            {
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


        // coming soon, could be scrapped if time is tight...
//    public static void customWeaponNameScreen()
//    {
//
//    }



    public static void potionSelectionScreen()
    {
        System.out.println();
        System.out.println("Potion Order");
        System.out.println("----------------------------------");
        System.out.println("Please select a potion");
        System.out.println("1) Small Potion (Restores 50HP)");
        System.out.println("2) Medium Potion (Restores 100HP)");
        System.out.println("3) Large Potion (Restores 300HP)");
        System.out.println("4) Max Potion (Restores ALL HP)");
        System.out.println("5) Elixir (Restores ALL HP and MP)");
        System.out.println("0) Go Back");

        while (true)
        {
            String choice = getUserInput("Select an option: ");

            switch (choice)
            {
                case "1":
                    Potion potion = new Potion("Small Potion", 100);
                    order.addValuable(potion);
                    System.out.println();
                    System.out.println(potion.getName() + " has been added to your order");
                    orderScreen();
                    return;

                case "2":
                    Potion potion2 = new Potion("Medium Potion", 200);
                    order.addValuable(potion2);
                    System.out.println();
                    System.out.println(potion2.getName() + " has been added to your order");
                    orderScreen();
                    return;

                case "3":
                    Potion potion3 = new Potion("Large Potion", 300);
                    order.addValuable(potion3);
                    System.out.println();
                    System.out.println(potion3.getName() + " has been added to your order");
                    orderScreen();
                    return;

                case "4":
                    Potion potion4 = new Potion("Max Potion", 500);
                    order.addValuable(potion4);
                    System.out.println();
                    System.out.println(potion4.getName() + " has been added to your order");
                    orderScreen();
                    return;

                case "5":
                    Potion potion5 = new Potion("Elixir", 1000);
                    order.addValuable(potion5);
                    System.out.println();
                    System.out.println(potion5.getName() + " has been added to your order");
                    orderScreen();
                    return;

                case "0":
                    orderScreen();
                    break;

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }
    }



    public static void itemSelectionScreen()
    {
        System.out.println();
        System.out.println("Item Order");
        System.out.println("----------------------------------");
        System.out.println("Please select an item:");
        System.out.println("1) Smoke Ball (Escape from any Battle) ($200)");
        System.out.println("2) Teleport Stone (Escape from any dungeon) ($200)");
        System.out.println("3) Bomb (Deals damage) ($200)");
        System.out.println("0) Go Back");

        while (true)
        {
            String choice = getUserInput("Select an option: ");

            switch (choice)
            {
                case "1":
                    Item item = new Item("Smoke Ball", 200);
                    order.addValuable(item);
                    System.out.println();
                    System.out.println(item.getName() + " has been added to your order");
                    orderScreen();
                    return;

                case "2":
                    Item item2 = new Item("Teleport Stone", 200);
                    order.addValuable(item2);
                    System.out.println();
                    System.out.println(item2.getName() + " has been added to your order");
                    orderScreen();
                    return;

                case "3":
                    Item item3 = new Item("Bomb", 200);
                    order.addValuable(item3);
                    System.out.println();
                    System.out.println(item3.getName() + " has been added to your order");
                    orderScreen();
                    return;

                case "0":
                    orderScreen();
                    break;

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }
    }


    // STILL not finished
    public static void checkoutScreen()
    {
        System.out.println();
        System.out.println("Your Order");
        System.out.println("--------------------------------------------------");

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

        // prints the total price using my getTotal() method
        System.out.println("Total: $" + order.getTotal());

        System.out.println();
        System.out.println("What would you like to do?");
        // prints the order into its own .txt receipt file inside the receipts directory
        System.out.println("Y) Confirm Order");
        // remove an individual item from order
        System.out.println("R) Remove an Item from your Order");
        // shows the user their order again
        System.out.println("S) Show my Order again");
        // discards order completely
        System.out.println("N) Cancel Order");
        // goes back to the last screen, (add another item)
        System.out.println("0) Go Back (Forgot something?)");

        while (true)
        {
            String choice = getUserInput("Make your selection here: ");

            switch (choice)
            {
                case "Y":
                    ReceiptWriter.writeOrder(order);
                    // wipes the order
                    order = new Order();
                    homeScreen();
                    return;

                case "R":
                    // INSERT NEW SCREEN HERE... ZZZ
                    break;

                case "S":
                    checkoutScreen();
                    return;

                case "N":
                    // wipes the order
                    order = new Order();
                    homeScreen();
                    return;

                case "0":
                    orderScreen();
                    break;

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }

    }


    // helper method
    public static String getUserInput(String message)
    {
        System.out.print(message);
        return userInput.nextLine().trim().toUpperCase();
    }

}
