package org.example.management;

import org.example.entity.Location;

public class LocationManagement {

    // Add
    private static Location askForLocationToAdd() {
        Location location = new Location();

        location.setId(GeneralManagement.getMaxIdLocation() + 1);
        location.setName(GeneralManagement.askForInput("name"));
        location.setType(GeneralManagement.askForInput("type"));
        location.setDimension(GeneralManagement.askForInput("dimension"));

        return location;
    }
    public static void addLocation() {
        Location location = askForLocationToAdd();
        GeneralManagement.add(location);
    }

    // Edit
    public static void editLocation() {
        Location location = GeneralManagement.askForEntityToEditOrDelete("edit", Location.class);

        assert location != null;

        if (GeneralManagement.askForFieldToEditOrNot("name")) { location.setName(GeneralManagement.askForInput("name")); }
        if (GeneralManagement.askForFieldToEditOrNot("type")) { location.setType(GeneralManagement.askForInput("type")); }
        if (GeneralManagement.askForFieldToEditOrNot("dimension")) { location.setDimension(GeneralManagement.askForInput("dimension")); }

        GeneralManagement.edit(location);
        System.out.println("Location with Id " + location.getId() + " updated successfully.");
    }

    // Delete
    public static void deleteLocation() {
        Location location = GeneralManagement.askForEntityToEditOrDelete("delete", Location.class);

        assert location != null;

        if (GeneralManagement.askToDeleteLocation(location).equalsIgnoreCase("y")) {
            GeneralManagement.delete(location);
            System.out.println("Location " + location.getName() + " deleted successfully.");
        } else {
            System.out.println("Location " + location.getName() + " was not deleted.");
        }
    }

    // Search
    public static void searchLocationByText() {
        GeneralManagement.searchByText(Location.class);
    }
    public static void SearchLocationWithoutCharacters() {
        GeneralManagement.locationsWithoutCharacters();
    }
}
