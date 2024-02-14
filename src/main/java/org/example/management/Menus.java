package org.example.management;

import java.util.Scanner;

import static org.example.management.CharManagement.*;
import static org.example.management.EpisodeManagement.*;
import static org.example.management.LocationManagement.*;

public class Menus {

    private static final Scanner sc = new Scanner(System.in);

    //Main menu
    public static void mainMenu() {

        String option;
        do {
            showMainMenu();
            option = getOption();

            switch (option) {
                case "1":
                    characterMenu();
                    break;
                case "2":
                    locationMenu();
                    break;
                case "3":
                    episodeMenu();
                    break;
                case "E":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option");
                    System.out.println();
            }
        } while (!option.equals("E"));
    }

    //First level menu
    private static void characterMenu() {
        String option;
        do {
            showSubMenu("character");
            option = getOption();

            switch (option) {
                case "1":
                    searchCharacterMenu();
                    break;
                case "2":
                    addChar();
                    break;
                case "3":
                    editChar();
                    break;
                case "4":
                    deleteChar();
                    break;
                case "B":
                    break;
                default:
                    System.out.println("Invalid option");
                    System.out.println();
            }
        } while (!option.equals("B"));
    }

    private static void locationMenu() {
        String option;
        do {
            showSubMenu("location");
            option = getOption();

            switch (option) {
                case "1":
                    searchLocationMenu();
                    break;
                case "2":
                    AddLocation();
                    break;
                case "3":
                    EditLocation();
                    break;
                case "4":
                    DeleteLocation();
                    break;
                case "B":
                    break;
                default:
                    System.out.println("Invalid option");
                    System.out.println();
            }
        } while (!option.equals("B"));
    }

    private static void episodeMenu() {
        String option;
        do {
            showSubMenu("episode");
            option = getOption();

            switch (option) {
                case "1":
                    searchEpisodeMenu();
                    break;
                case "2":
                    AddEpisode();
                    break;
                case "3":
                    EditEpisode();
                    break;
                case "4":
                    DeleteEpisode();
                    break;
                case "B":
                    break;
                default:
                    System.out.println("Invalid option");
                    System.out.println();
            }
        } while (!option.equals("B"));
    }

    //Second level menu
    private static void searchCharacterMenu() {
        String option;
        do {
            showSearchSubMenu("character");
            option = getOption();

            switch (option) {
                case "1":
                    searchCharByText();
                    break;
                case "2":
                    searchCharWithoutEpisodes();
                    break;
                case "B":
                    break;
                default:
                    System.out.println("Invalid option");
                    System.out.println();
            }
        } while (!option.equals("B"));
    }

    private static void searchLocationMenu() {
        String option;
        do {
            showSearchSubMenu("location");
            option = getOption();

            switch (option) {
                case "1":
                    SearchLocationByText();
                    break;
                case "2":
                    SearchLocationWithoutCharacters();
                    break;
                case "B":
                    break;
                default:
                    System.out.println("Invalid option");
                    System.out.println();
            }
        } while (!option.equals("B"));
    }

    private static void searchEpisodeMenu() {
        String option;
        do {
            showSearchSubMenu("episode");
            option = getOption();

            switch (option) {
                case "1":
                    SearchEpisodeByText();
                    break;
                case "2":
                    SearchEpisodeMostChars();
                    break;
                case "B":
                    break;
                default:
                    System.out.println("Invalid option");
                    System.out.println();
            }
        } while (!option.equals("B"));
    }

    //Auxiliary methods
    private static void showMainMenu() {
        System.out.println("---------------------------------");
        System.out.println("1. Character management \uD83D\uDC7D"); // 👽
        System.out.println("2. Locations management \uD83C\uDF0D"); // 🌍
        System.out.println("3. Episodes management \uD83C\uDFAC"); // 🎬
        System.out.println("E. Exit");
        System.out.println();
    }

    private static void showSubMenu(String dataType) {
        System.out.println("---------------------------------");
        System.out.println("1. Search " + dataType + " \uD83D\uDD0D"); // 🔍a
        System.out.println("2. Add " + dataType + " ➕"); // ➕
        System.out.println("3. Edit " + dataType + " ✏️"); // ✏️
        System.out.println("4. Delete " + dataType + " \uD83D\uDDD1️"); // 🗑️
        System.out.println("B. Back \uD83D\uDD19");
        System.out.println();
    }

    private static void showSearchSubMenu(String dataType) {

        System.out.println("---------------------------------");
        System.out.println("1. Search " + dataType + " by text");

        switch(dataType) {
            case "character":
                System.out.println("2. Search " + dataType + " without episodes");
                break;
            case "location":
                System.out.println("2. Search " + dataType + " without characters");
                break;
            case "episode":
                System.out.println("2. Search " + dataType + " with the most characters");
                break;
        }
        System.out.println("B. Back \uD83D\uDD19");
        System.out.println();
    }

    private static String getOption() {
        System.out.print("Enter an option: ");
        return sc.nextLine().toUpperCase();
    }

}
