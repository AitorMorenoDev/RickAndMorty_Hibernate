package org.example.management;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.entity.Location;

import java.util.List;
import java.util.Scanner;

public class LocationManagement {

    private static final Scanner sc = new Scanner(System.in);
    private static EntityManager em;

    public LocationManagement(EntityManager em) {
        LocationManagement.em = em;
    }

    // Add
    public static Location askForLocationToAdd() {
        Location location = new Location();

        location.setId(getMaxIdLocation() + 1);
        location.setName(GeneralManagement.askForInput("name"));
        location.setType(GeneralManagement.askForInput("type"));
        location.setDimension(GeneralManagement.askForInput("dimension"));

        return location;
    }
    public static void AddLocation() {
        Location location = askForLocationToAdd();
        em.persist(location);
    }


    // Edit and Delete
    public static Location askForLocationToEditOrDelete(String action) {

        if (getLocations().isEmpty()) {
            System.out.println("There are no locations to " + action + ".");
            return null;
        }

        for (Location location: getLocations()) {
            System.out.println(location.getId() + " - " + location.getName());
        }

        int idLocation;
        do {
            System.out.println("--------------------");
            System.out.println("Enter the id of the location you want to " + action + ":");
            idLocation = sc.nextInt();

            if (idLocation < 1 || idLocation > getMaxIdLocation()) {
                System.out.println("Invalid option. Enter a valid option.");
            }
        } while (idLocation < 1 || idLocation > getMaxIdLocation());

        return em.find(Location.class, idLocation);

    }
    public static void EditLocation() {
        Location location = askForLocationToEditOrDelete("edit");

        sc.nextLine();
        assert location != null;

        if (GeneralManagement.askForFieldToEditOrNot("name")) { location.setName(GeneralManagement.askForInput("name")); }
        if (GeneralManagement.askForFieldToEditOrNot("type")) { location.setType(GeneralManagement.askForInput("type")); }
        if (GeneralManagement.askForFieldToEditOrNot("dimension")) { location.setDimension(GeneralManagement.askForInput("dimension")); }

        em.merge(location);
        System.out.println("Location with Id " + location.getId() + " updated successfully.");
    }
    public static void DeleteLocation() {
        Location location = askForLocationToEditOrDelete("delete");
        assert location != null;
        sc.nextLine();

        System.out.println("Are you sure you want to delete the location " + location.getName() + "? (y to confirm, any other key to cancel): ");

        if (sc.nextLine().equalsIgnoreCase("y")) {
            em.remove(location);
            System.out.println("Location " + location.getName() + " deleted successfully.");
        } else {
            System.out.println("Location " + location.getName() + " was not deleted.");
        }
    }

    // Search
    public static void SearchLocationByText() {
        GeneralManagement.searchByText(Location.class);
    }
    public static void SearchLocationWithoutCharacters() {
        System.out.println("SearchLocationByType");
    }


    // Auxiliary methods
    private static int getMaxIdLocation() {
        Query query = em.createQuery("SELECT max (l.id) from Location l");
        return (int) query.getSingleResult();
    }
    private static List<Location> getLocations() {
        Query query = em.createQuery("from Location");
        return query.getResultList();
    }

}
