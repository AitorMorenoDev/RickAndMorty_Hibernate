package org.example.management;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaQuery;
import org.example.entity.Characters;
import org.example.entity.Episode;
import org.example.entity.Location;
import org.hibernate.query.NativeQuery;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneralManagement {

    private static final Scanner sc = new Scanner(System.in);
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction trans;


    // Connection and database management methods
    public static void tearUp() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        trans = em.getTransaction();

        if (trans != null) {
            trans.begin();
        } else {
            System.out.println("Error starting transaction");
        }
    }
    public static void tearDown() {
        if (trans.isActive()) {
            trans.commit();
        } else {
            trans.rollback();
        }
        if (em.isOpen() && em != null) {
            em.close();
        }
        if (emf.isOpen() && emf != null) {
            emf.close();
        }
    }
    public static void deactivateLog() {
        @SuppressWarnings("unused")
        Logger logger = Logger.getLogger("org.hibernate");
        java.util.logging.LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);
    }


    // Methods for CRUD operations
    public static <T> Boolean add(T entity) {
        tearUp();
        em.persist(entity);
        tearDown();
        return true;
    }
    public static <T> Boolean edit(T entity) {
        tearUp();
        em.merge(entity);
        tearDown();
        return true;
    }
    public static <T> Boolean delete(T entity) {
        tearUp();
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.remove(entity);
        tearDown();
        return true;
    }
    public static <T> void searchByText(Class<T> entityClass) {

        System.out.print("Enter the text to search for " + entityClass.getSimpleName() + ": ");
        String text = sc.nextLine();

        tearUp();
        Query query = em.createQuery("from " + entityClass.getSimpleName() + " e where lower(e.name) like :text");
        query.setParameter("text", "%" + text.toLowerCase() + "%");

        List<?> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            System.out.println("No " + entityClass.getSimpleName() + " found with the name " + text);
        } else {
            showResults(resultList);
        }
        tearDown();
    }


    // Auxiliary methods for CRUD operations
    public static <T> T askForEntityToEditOrDelete(String action, Class<T> entityClass) {
        List<T> entities = getEntities(entityClass);

        if (entities.isEmpty()) {
            System.out.println("There are no " + entityClass.getSimpleName() + " to " + action + ".");
            return null;
        }

        showResults(entities);

        int idEntity;
        do {
            System.out.println("---------------------------------");
            System.out.print("Enter the " + entityClass.getSimpleName() + " you want to " + action + ": ");
            idEntity = sc.nextInt();

            if (idEntity < 1 || idEntity > getMaxIdForEntity(entityClass)) {
                System.out.println("Invalid option. Enter a valid option.");
            }
        } while (idEntity < 1 || idEntity > getMaxIdForEntity(entityClass));

        sc.nextLine();

        T entity = em.find(entityClass, idEntity);

        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }

        return entity;
    }
    public static void showResults(List<?> results) {
        if (results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            for (Object result : results) {
                if (result instanceof Characters)
                    System.out.println(((Characters) result).getId() + " - " + ((Characters) result).getName());
                else if (result instanceof Location)
                    System.out.println(((Location) result).getId() + " - " + ((Location) result).getName());
                else if (result instanceof Episode)
                    System.out.println(((Episode) result).getId() + " - " + ((Episode) result).getName());
            }
        }
    }
    public static String askToDeleteChar(Characters character) {
        if (character == null) {
            System.out.println("There are no characters to delete.");
        }
        assert character != null;
        if (character.getEpisodes().isEmpty()) {
            System.out.print("Are you sure you want to delete " + character.getName() + "? (y to confirm, any other key to cancel): ");
        } else {
            System.out.print("Character " + character.getName() +
                    " has episodes related. Are you sure you want to delete it? (y to confirm, any other key to cancel): ");
        }
        return sc.nextLine();
    }
    public static String askToDeleteLocation (Location location) {
        if (location == null) {
            System.out.println("There are no locations to delete.");
        }

        assert location != null;
        System.out.print("Are you sure you want to delete " + location.getName() + "? (y to confirm, any other key to cancel): ");
        return sc.nextLine();
    }
    public static String askToDeleteEpisode (Episode episode) {
        if (episode == null) {
            System.out.println("There are no episodes to delete.");
        }

        assert episode != null;
        if (episode.getCharacters().isEmpty()) {
            System.out.print("Are you sure you want to delete " + episode.getName() + "? (y to confirm, any other key to cancel): ");
        } else {
            System.out.print("Episode " + episode.getName() +
                    " has characters related. Are you sure you want to delete it? (y to confirm, any other key to cancel): ");
        }
        return sc.nextLine();
    }


    // Methods for specific searches
    public static void charsWithoutEpisodes() {
        tearUp();
        Query query = em.createQuery("from Characters c where c.episodes is empty");
        List<Characters> characters = query.getResultList();
        for (Characters character: characters) {
            System.out.println(character.getId() + " - " + character.getName());
        }
    }
    public static void locationsWithoutCharacters() {
        tearUp();

        Query query = em.createQuery("from Location l where l.id not in (select c.idLocation from Characters c) " +
                "and l.id not in (select c.idOrigin from Characters c)");
        List<Location> locations = query.getResultList();
        for (Location location: locations) {
            System.out.println(location.getId() + " - " + location.getName());
        }
    }
    public static void searchEpisodesByText() {
        // Search episodes by text using native query
        System.out.print("Enter the text to search for episodes: ");
        String text = sc.nextLine();

        String sqlQuery = "SELECT * FROM episode WHERE lower(name) LIKE :text";

        tearUp();
        NativeQuery query = (NativeQuery) em.createNativeQuery(sqlQuery, Episode.class);
        query.setParameter("text", "%" + text.toLowerCase() + "%");

        List<Episode> episodes = query.getResultList();


        if (episodes.isEmpty()) {
            System.out.println("No episodes found with the name " + text);
        } else {
            for (Episode episode: episodes) {
                System.out.println(episode.getId() + " - " + episode.getName() + " - " + episode.getEpisode());
            }
        }

        tearDown();
    }
    public static void episodeWithMostCharacters() {
        tearUp();

        String sqlQuery = "SELECT * from episode WHERE id in " +
                "(SELECT id_episode from character_in_episode " +
                "group by id_episode order by count(id_character) desc" +
                " limit 1)";

        NativeQuery query = (NativeQuery) em.createNativeQuery(sqlQuery, Episode.class);
        Episode episode = (Episode) query.getSingleResult();
        System.out.println("The episode with most characters is ["
                + episode.getId() + " - " + episode.getName() + " - "
                + episode.getEpisode() + "] with " + episode.getCharacters().size() + " characters.");
    }


    // Another auxiliary methods
    public static <T> List<T> getEntities(Class<T> entity) {
        tearUp();
        CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entity);
        cq.select(cq.from(entity));
        return em.createQuery(cq).getResultList();
    }
    public static int getMaxIdForEntity(Class<?> entityClass) {
        tearUp();
        Query query = em.createQuery("SELECT max (e.id) from " + entityClass.getSimpleName() + " e");
        return (int) query.getSingleResult();
    }
    public static String askForInput(String type) {
        String input;
        do {
            System.out.println("---------------------------------");
            System.out.print("Enter the " + type + ": ");
            input = sc.nextLine();

            if (input == null || input.isEmpty()) {
                System.out.println("Invalid input. Enter a valid input.");
            }
        } while (input == null || input.isEmpty());

        return input;
    }
    public static Boolean askForFieldToEditOrNot(String type) {
        System.out.println("---------------------------------");
        System.out.print("Do you want to edit the " + type + "? (y to confirm, any other key to cancel): ");
        return sc.nextLine().equalsIgnoreCase("y");
    }
    public static int getMaxIdChar() {
        tearUp();
        Query query = em.createQuery("SELECT max (c.id) from Characters c");
        return (int) query.getSingleResult();
    }
    public static int getMaxIdLocation() {
        tearUp();
        Query query = em.createQuery("SELECT max (l.id) from Location l");
        return (int) query.getSingleResult();
    }
    public static int getMaxIdEpisode() {
        tearUp();
        Query query = em.createQuery("SELECT max (e.id) from Episode e");
        return (int) query.getSingleResult();
    }
    public static List<Location> getLocations() {
        tearUp();
        Query query = em.createQuery("from Location");
        return query.getResultList();
    }
}
