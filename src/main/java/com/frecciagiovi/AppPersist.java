package com.frecciagiovi;

import com.frecciagiovi.model.Stazione;
import com.frecciagiovi.model.Treno;
import com.frecciagiovi.model.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class AppPersist {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DefaultPersistenceUnit");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Utente u1 = new Utente();
        u1.setNome("Giubba");
        u1.setCognome("Chattini");
        u1.setEmail("escanor@mail.com");
        u1.setDataNascita(LocalDate.of(1991, 5, 23));
        entityManager.persist(u1);

        Utente u2 = new Utente();
        u2.setNome("Gioviz");
        u2.setCognome("Nelcastello");
        u2.setEmail("lammaildigiovi@mail.com");
        u2.setDataNascita(LocalDate.of(1992, 5, 23));
        entityManager.persist(u2);


        Treno tr1 = new Treno();
        tr1.set

        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();

    }
}
