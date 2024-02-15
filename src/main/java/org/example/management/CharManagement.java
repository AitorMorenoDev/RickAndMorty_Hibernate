package org.example.management;

import org.example.entity.Characters;
import org.example.entity.Location;

import java.util.Scanner;

public class CharManagement {

    private static final Scanner sc = new Scanner(System.in);

    // Add
    private static Characters askForCharToAdd() {

        Characters character = new Characters();

        character.setId(GeneralManagement.getMaxIdChar() + 1);
        character.setName(GeneralManagement.askForInput("name"));
        character.setStatus(askForStatus());
        character.setSpecies(askForSpecies());
        character.setType(GeneralManagement.askForInput("type"));
        character.setGender(askForGender());
        character.setIdOrigin(askForLocation("origin"));
        character.setIdLocation(askForLocation("location"));

        return character;
    }
    public static void addChar() {
        Characters character = askForCharToAdd();
        GeneralManagement.add(character);
    }

    // Edit
    public static void editChar() {

        Characters character = GeneralManagement.askForEntityToEditOrDelete("edit", Characters.class);

        assert character != null;

        if (GeneralManagement.askForFieldToEditOrNot("name")) { character.setName(GeneralManagement.askForInput("name")); }
        if (GeneralManagement.askForFieldToEditOrNot("status")) { character.setStatus(askForStatus()); }
        if (GeneralManagement.askForFieldToEditOrNot("species")) { character.setSpecies(askForSpecies()); }
        if (GeneralManagement.askForFieldToEditOrNot("gender")) { character.setGender(askForGender()); }
        if (GeneralManagement.askForFieldToEditOrNot("type")) { character.setType(GeneralManagement.askForInput("type")); }
        if (GeneralManagement.askForFieldToEditOrNot("origin")) { character.setIdOrigin(askForLocation("origin")); }
        if (GeneralManagement.askForFieldToEditOrNot("location")) { character.setIdLocation(askForLocation("location")); }

        GeneralManagement.edit(character);
        System.out.println("Character with Id " + character.getId() + " updated successfully.");
    }

    // Delete
    public static void deleteChar() {
        Characters character = GeneralManagement.askForEntityToEditOrDelete("delete", Characters.class);

        assert character != null;

        if (GeneralManagement.askToDeleteChar(character).equalsIgnoreCase("y")) {
            GeneralManagement.delete(character);
            System.out.println("Character " + character.getName() + " deleted.");
        } else {
            System.out.println("Character " + character.getName() + " not deleted.");
        }
    }

    // Search
    public static void searchCharByText() {
        GeneralManagement.searchByText(Characters.class);
    }
    public static void searchCharWithoutEpisodes() {
        GeneralManagement.charsWithoutEpisodes();
    }


    // Auxiliary methods
    private static String askForStatus() {
        int choice;
        do {
            System.out.println("---------------------------------");
            System.out.println("1. Alive \uD83D\uDE4B"); // 🙋
            System.out.println("2. Dead \uD83D\uDC80"); // 💀
            System.out.println("3. Unknown ❓"); // ❓
            System.out.println();
            System.out.print("Choose the status: ");
            choice = sc.nextInt();
        } while (choice < 1 || choice > 3);

        sc.nextLine();

        switch (choice) {
            case 1: return "Alive";
            case 2: return "Dead";
            case 3: return "Unknown";
            default: return null;
        }
    }
    private static String askForSpecies() {
        int choice;
        do {
            System.out.println("---------------------------------");
            System.out.println("1. Alien \uD83D\uDC7D"); // 👽
            System.out.println("2. Animal \uD83D\uDC18"); // 🐘
            System.out.println("3. Cronenberg \uD83D\uDC19"); // 🐙
            System.out.println("4. Disease \uD83E\uDDA0"); // 🦠
            System.out.println("5. Human \uD83D\uDC74"); // 👴
            System.out.println("6. Humanoid \uD83E\uDDCC"); // 🧌
            System.out.println("7. Mythological Creature \uD83E\uDDDC"); // 🧜
            System.out.println("8. Poopybutthole \uD83D\uDCA9"); // 💩
            System.out.println("9. Robot \uD83E\uDD16"); // 🤖
            System.out.println("10. Unknown ❓");
            System.out.println();
            System.out.print("Choose the species: ");
            choice = sc.nextInt();
        } while (choice < 1 || choice > 10);

        sc.nextLine();

        switch(choice) {
            case 1: return "Alien";
            case 2: return "Animal";
            case 3: return "Cronenberg";
            case 4: return "Disease";
            case 5: return "Human";
            case 6: return "Humanoid";
            case 7: return "Mythological Creature";
            case 8: return "Poopybutthole";
            case 9: return "Robot";
            case 10: return "Unknown";
            default: return null;
        }
    }
    private static String askForGender() {
        int choice;
        do {
            System.out.println("---------------------------------");
            System.out.println("1. Male \uD83D\uDD7A"); // 🕺
            System.out.println("2. Female \uD83D\uDC83"); // 💃
            System.out.println("3. Genderless ⛔"); // ⛔
            System.out.println("4. Unknown ❓");
            System.out.println();
            System.out.print("Choose the gender: ");
            choice = sc.nextInt();
        } while (choice < 1 || choice > 4);

        sc.nextLine();

        switch (choice) {
            case 1: return "Male";
            case 2: return "Female";
            case 3: return "Genderless";
            case 4: return "Unknown";
            default: return null;
        }
    }
    private static Location askForLocation(String type) {
        int index = 0;
        for (Location location: GeneralManagement.getLocations()) {
            System.out.println(index + " - " + location.getName());
            index++;
        }

        System.out.print("Choose the " + type + ": ");
        int response;

        do {
            response = sc.nextInt();
            if (response > GeneralManagement.getMaxIdLocation() || response < 0) {
                System.out.println("Invalid option. Enter a valid option.");
            }
        } while (response > GeneralManagement.getMaxIdLocation() || response < 0);

        sc.nextLine();

        return new Location (response);
    }
}

