package org.example;

import org.example.management.GeneralManagement;
import org.example.management.Menus;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("Welcome to the Rick & Morty™ Hibernate API! \uD83D\uDCFA"); // 📺

        GeneralManagement.deactivateLog();
        Menus.mainMenu();
    }
}
