package com.frecciagiovi;

import com.frecciagiovi.model.Stazione;
import com.frecciagiovi.model.Treno;
import com.frecciagiovi.model.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppPersist {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DefaultPersistenceUnit");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Stazione s1 = new Stazione();
        s1.setNomeStazione("Bologna Centrale");
        entityManager.persist(s1);

        Stazione s2 = new Stazione();
        s2.setNomeStazione("Milano Centrale");
        entityManager.persist(s2);

        Stazione s3 = new Stazione();
        s3.setNomeStazione("Modena");
        entityManager.persist(s3);

        Stazione s4 = new Stazione();
        s4.setNomeStazione("Roma Termini");
        entityManager.persist(s4);

        Stazione s5 = new Stazione();
        s5.setNomeStazione("Napoli Centrale");
        entityManager.persist(s5);

        Stazione s6 = new Stazione();
        s6.setNomeStazione("Firenze Santa Maria Novella");
        entityManager.persist(s6);

        Stazione s7 = new Stazione();
        s7.setNomeStazione("Torino Porta Nuova");
        entityManager.persist(s7);

        Stazione s8 = new Stazione();
        s8.setNomeStazione("Venezia Santa Lucia");
        entityManager.persist(s8);

        Stazione s9 = new Stazione();
        s9.setNomeStazione("Verona Porta Nuova");
        entityManager.persist(s9);

        Utente u1 = new Utente();
        u1.setNome("Giubba");
        u1.setCognome("Chattini");
        u1.setEmail("escanor@mail.com");
        u1.setDataNascita(LocalDate.of(1991, 5, 23));
        entityManager.persist(u1);

        Utente u2 = new Utente();
        u2.setNome("Gioviz");
        u2.setCognome("Nelcastello");
        u2.setEmail("giovi@mail.com");
        u2.setDataNascita(LocalDate.of(1992, 5, 23));
        entityManager.persist(u2);


        // Treno 1 – Bologna → Torino
        Treno tr1 = new Treno();
        tr1.setNumeroTreno("FR-8896");
        tr1.setDestinazione(s7); // Torino Porta Nuova
        tr1.setOraPartenza(LocalDateTime.of(2026, 1, 14, 15, 30));
        tr1.setBinario("22");
        tr1.setDurataTratta(Duration.ofMinutes(220)); // 3h 40m
        tr1.setStazione(s1); // Bologna Centrale
        entityManager.persist(tr1);

// Treno 2 – Milano → Firenze
        Treno tr2 = new Treno();
        tr2.setNumeroTreno("FR-1021");
        tr2.setDestinazione(s6); // Firenze SMN
        tr2.setOraPartenza(LocalDateTime.of(2026, 1, 14, 16, 45));
        tr2.setBinario("10");
        tr2.setDurataTratta(Duration.ofMinutes(115));
        tr2.setStazione(s2); // Milano Centrale
        entityManager.persist(tr2);

// Treno 3 – Bologna → Venezia
        Treno tr3 = new Treno();
        tr3.setNumeroTreno("IC-7744");
        tr3.setDestinazione(s8); // Venezia SL
        tr3.setOraPartenza(LocalDateTime.of(2026, 1, 14, 18, 20));
        tr3.setBinario("4");
        tr3.setDurataTratta(Duration.ofMinutes(160));
        tr3.setStazione(s1); // Bologna Centrale
        entityManager.persist(tr3);

// Treno 4 – Roma → Napoli
        Treno tr4 = new Treno();
        tr4.setNumeroTreno("FR-9010");
        tr4.setDestinazione(s5); // Napoli Centrale
        tr4.setOraPartenza(LocalDateTime.of(2026, 1, 15, 7, 50));
        tr4.setBinario("15");
        tr4.setDurataTratta(Duration.ofMinutes(70));
        tr4.setStazione(s4); // Roma Termini
        entityManager.persist(tr4);

// Treno 5 – Firenze → Bologna
        Treno tr5 = new Treno();
        tr5.setNumeroTreno("RV-3302");
        tr5.setDestinazione(s1); // Bologna Centrale
        tr5.setOraPartenza(LocalDateTime.of(2026, 1, 15, 9, 10));
        tr5.setBinario("6");
        tr5.setDurataTratta(Duration.ofMinutes(40));
        tr5.setStazione(s6); // Firenze SMN
        entityManager.persist(tr5);

// Treno 6 – Verona → Torino
        Treno tr6 = new Treno();
        tr6.setNumeroTreno("IC-6681");
        tr6.setDestinazione(s7); // Torino Porta Nuova
        tr6.setOraPartenza(LocalDateTime.of(2026, 1, 15, 11, 35));
        tr6.setBinario("11");
        tr6.setDurataTratta(Duration.ofMinutes(210));
        tr6.setStazione(s9); // Verona Porta Nuova
        entityManager.persist(tr6);

        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();

    }
}
