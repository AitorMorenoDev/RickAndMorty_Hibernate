package org.example.management;

import org.example.entity.Episode;

import java.time.LocalDateTime;
import java.util.Scanner;

public class EpisodeManagement {

    private static final Scanner sc = new Scanner(System.in);

    // Add
    private static Episode askForEpisodeToAdd() {
        Episode episode = new Episode();

        episode.setId(GeneralManagement.getMaxIdEpisode() + 1);
        episode.setName(GeneralManagement.askForInput("name"));
        episode.setAirDate(askForAirDate());
        episode.setEpisode(askForEpisode());

        return episode;
    }
    public static void AddEpisode() {
        Episode episode = askForEpisodeToAdd();
        GeneralManagement.add(episode);
    }

    // Edit
    public static void EditEpisode() {
        Episode episode = GeneralManagement.askForEntityToEditOrDelete("edit", Episode.class);

        assert episode != null;

        if (GeneralManagement.askForFieldToEditOrNot("name")) { episode.setName(GeneralManagement.askForInput("name")); }
        if (GeneralManagement.askForFieldToEditOrNot("air date")) { episode.setAirDate(askForAirDate()); }
        if (GeneralManagement.askForFieldToEditOrNot("episode")) { episode.setEpisode(askForEpisode()); }

        GeneralManagement.edit(episode);
        System.out.println("Episode with Id " + episode.getId() + " updated successfully.");
    }

    // Delete
    public static void DeleteEpisode() {
        Episode episode = GeneralManagement.askForEntityToEditOrDelete("delete", Episode.class);

        assert episode != null;

        if (GeneralManagement.askToDeleteEpisode(episode).equalsIgnoreCase("y")) {
            GeneralManagement.delete(episode);
            System.out.println("Episode " + episode.getName() + " deleted.");
        } else {
            System.out.println("Episode " + episode.getName() + " was not deleted.");
        }
    }

    // Search
    public static void SearchEpisodeByText() {
        GeneralManagement.searchEpisodesByText();
    }
    public static void SearchEpisodeMostChars() {
    GeneralManagement.episodeWithMostCharacters();
    }


    // Auxiliary methods
    private static LocalDateTime askForAirDate() {

        // Year
        int year;
        do {
            System.out.print("Enter the year of the episode: ");
            year = sc.nextInt();

            if (year < 2000 || year > 2050) {
                System.out.println("Invalid year. Enter a number between 2000 and 2050.");
            }
        } while (year < 2000 || year > 2050);

        // Month
        int month;
        do {
            System.out.print("Enter the month of the episode: ");
            month = sc.nextInt();

            if (month < 1 || month > 12) {
                System.out.println("Invalid month. Enter a number between 1 and 12.");
            }
        } while (month < 1 || month > 12);

        // Day
        int day;
        int maxDay;
        do {
            System.out.print("Enter the day of the episode: ");
            day = sc.nextInt();

            switch (month) {
                case 2:
                    maxDay = 28;
                    break;
                case 4: case 6: case 9: case 11:
                    maxDay = 30;
                    break;
                default:
                    maxDay = 31;
            }

            if (day < 1 || day > maxDay) {
                System.out.println("Invalid day. Enter a number between 1 and " + maxDay + " for the month " + month + ".");
            }

        } while (day < 1 || day > maxDay);

        return LocalDateTime.of(year, month, day, 0, 0);
    }
    private static String askForEpisode() {

        int season;
        do {
            System.out.print("Enter the season (season 6 in advance): ");
            season = sc.nextInt();

            if (season < 6) {
                System.out.println("Invalid season. You must enter an episode from season 6 or later.");
            }
        } while (season < 6);

        System.out.print("Enter the episode: ");
        int episode = sc.nextInt();

        String formattedSession = String.format("%02d", season);
        String formattedEpisode = String.format("%02d", episode);

        return "S" + formattedSession + "E" + formattedEpisode;
    }
}
