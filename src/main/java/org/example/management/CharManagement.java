package org.example.management;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entity.Characters;

import java.util.Scanner;

public class CharManagement {

    private static final Scanner sc = new Scanner(System.in);

    // Add
    public static void AddChar() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Characters character = askForChar();

        try {
            transaction.begin();
            entityManager.persist(character);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
    public static Characters askForChar() {

        Characters character = new Characters();
        int choice;

        // Set id
        // ID = Ver cuantos hay en la base de datos
        // character.setId(ID + 1);

        // Name
        System.out.println("Enter the name: ");
        character.setName(sc.nextLine());

        // Status
        do {
            System.out.println("---------------------------------");
            System.out.println("Choose the status: ");
            System.out.println("1. Alive");
            System.out.println("2. Dead");
            System.out.println("3. Unknown");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    character.setStatus("Alive");
                    break;
                case 2:
                    character.setStatus("Dead");
                    break;
                case 3:
                    character.setStatus("Unknown");
                    break;
                default:
                    System.out.println("Invalid option");
                    System.out.println();
            }
        } while (choice != 1 && choice != 2 && choice != 3);

        // Species
        do {
            System.out.println("---------------------------------");
            System.out.println("Choose the species: ");
            System.out.println("1. Alien");
            System.out.println("2. Animal");
            System.out.println("3. Cronenberg");
            System.out.println("4. Disease");
            System.out.println("5. Human");
            System.out.println("6. Humanoid");
            System.out.println("7. Mythological Creature");
            System.out.println("8. Poopybutthole");
            System.out.println("9. Robot");
            System.out.println("10. Unknown");
            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    character.setSpecies("Alien");
                    break;
                case 2:
                    character.setSpecies("Animal");
                    break;
                case 3:
                    character.setSpecies("Cronenberg");
                    break;
                case 4:
                    character.setSpecies("Disease");
                    break;
                case 5:
                    character.setSpecies("Human");
                    break;
                case 6:
                    character.setSpecies("Humanoid");
                    break;
                case 7:
                    character.setSpecies("Mythological Creature");
                    break;
                case 8:
                    character.setSpecies("Poopybutthole");
                    break;
                case 9:
                    character.setSpecies("Robot");
                    break;
                case 10:
                    character.setSpecies("Unknown");
                    break;
                default:
                    System.out.println("Invalid option");
                    System.out.println();
            }
        } while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6 && choice != 7 && choice != 8 && choice != 9 && choice != 10);

        // Type
        System.out.println("Enter the type: ");
        character.setType(sc.nextLine());

        // Gender
        do {
            System.out.println("---------------------------------");
            System.out.println("Choose the gender");
            System.out.println("1. Male");
            System.out.println("2. Female");
            System.out.println("3. Genderless");
            System.out.println("4. Unknown");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    character.setGender("Male");
                    break;
                case 2:
                    character.setGender("Female");
                    break;
                case 3:
                    character.setGender("Genderless");
                    break;
                case 4:
                    character.setGender("Unknown");
                    break;
                default:
                    System.out.println("Invalid option");
                    System.out.println();
            }
        } while (choice != 1 && choice != 2 && choice != 3 && choice != 4);

        // IdOrigin
        System.out.println("Enter the id of the origin: ");

        // IdLocation
        System.out.println("Enter the id of the location: ");

        return character;
    }

    // Edit
    public static void EditChar() {
        System.out.println("EditCharacter");
    }
    public static void AskForEdit() {
        System.out.println("AskForEdit");
    }

    // Delete
    public static void DeleteChar() {
        System.out.println("DeleteCharacter");
    }

    // Search
    public static void SearchCharByText() {
        System.out.println("SearchCharacterByText");
    }

    public static void SearchCharWithoutEpisodes() {
        System.out.println("SearchCharacterWithoutEpisodes");
    }
}

