package com.frecciagiovi.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class JpaUtil {

    private static EntityManagerFactory factory;
    private static final Logger logger = LogManager.getLogger(JpaUtil.class);

    private JpaUtil() {}

    public static void init() {
        if (factory == null) {
            logger.info("Inizializzazione JPA");
            factory = Persistence.createEntityManagerFactory("DefaultPersistenceUnit");
        }
    }

    public static EntityManager getEntityManager() {
        if (factory == null) {
            throw new IllegalStateException("JPA non inizializzato. Chiama JpaUtil.init()");
        }
        return factory.createEntityManager();
    }

    public static void shutdown() {
        if (factory != null) {
            factory.close();
            factory = null;
        }
    }
}
