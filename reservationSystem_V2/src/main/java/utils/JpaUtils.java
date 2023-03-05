package utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtils {
    private static EntityManagerFactory factory;
    static{
       factory = Persistence.createEntityManagerFactory("a21da08");

    }

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}