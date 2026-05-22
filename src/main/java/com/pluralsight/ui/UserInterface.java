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
                    // insert here
                    break;

                case "0":
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }
    }






    public static String getUserInput(String message)
    {
        System.out.print(message);
        return userInput.nextLine().trim();
    }

}
