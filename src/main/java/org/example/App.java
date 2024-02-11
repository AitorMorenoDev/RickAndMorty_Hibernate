package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entity.Characters;
import org.example.entity.Episode;
import org.example.entity.Location;
import org.example.management.Menus;

public class App
{
    public static void main( String[] args )
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Characters character = new Characters();
            Episode episode = new Episode();
            Location location = new Location();


            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

        System.out.println("Welcome to the Rick & Morty™ Hibernate API! \uD83D\uDCFA"); // 📺
        Menus.mainMenu();
    }
}
