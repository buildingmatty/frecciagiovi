package com.frecciagiovi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoUtil {
    private static EntityManagerFactory factory;
    protected EntityManager entityManager;

    private static final Logger logger = LogManager.getLogger(DaoUtil.class);


    DaoUtil(){entityManager = factory.createEntityManager();}

    public static void init(){
        logger.info("Inizializzazione JPA");
        factory = Persistence.createEntityManagerFactory("DefaultPersistenceUnit");
    }

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
