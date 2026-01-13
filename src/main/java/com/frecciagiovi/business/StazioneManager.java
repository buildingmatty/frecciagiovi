package com.frecciagiovi.business;
import com.frecciagiovi.model.Stazione;
import com.frecciagiovi.persistence.JpaUtil;
import com.frecciagiovi.repository.StazioneRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class StazioneManager {

    private static final Logger logger = LogManager.getLogger(StazioneManager.class);

    public static Stazione getStazioneById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id non valido");
        }

        var em = JpaUtil.getEntityManager();
        var repo = new StazioneRepository(em);

        try {
            return repo.findById(id).orElse(null);
        } finally {
            em.close();
        }
    }

    public static List<Stazione> getStazioni() {
        var em = JpaUtil.getEntityManager();
        var repo = new StazioneRepository(em);

        try {
            return repo.findAllOrderByNome();
        } finally {
            em.close();
        }
    }

    public static Stazione addStazione(Stazione s) {
        if (s == null || s.getNomeStazione() == null || s.getNomeStazione().isBlank()) {
            throw new IllegalArgumentException("nomeStazione obbligatorio");
        }

        var em = JpaUtil.getEntityManager();
        var repo = new StazioneRepository(em);

        try {
            em.getTransaction().begin();

            if (repo.existsByNome(s.getNomeStazione())) {
                throw new IllegalStateException("Stazione già esistente: " + s.getNomeStazione());
            }

            repo.save(s);

            em.getTransaction().commit();
            logger.info("Stazione creata: {}", s.getNomeStazione());
            return s;

        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.error("Errore creazione stazione", e);
            throw e;
        } finally {
            em.close();
        }

    }

    public static void deleteStazioneById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id non valido");
        }

        var em = JpaUtil.getEntityManager();
        var repo = new StazioneRepository(em);

        try {
            em.getTransaction().begin();

            var stazione = repo.findById(id).orElse(null);
            if (stazione == null) {
                throw new IllegalStateException("Stazione non trovata: " + id);
            }

            repo.delete(stazione);

            em.getTransaction().commit();
            logger.info("Stazione eliminata: id={}", id);

        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.error("Errore eliminazione stazione: id={}", id, e);
            throw e;
        } finally {
            em.close();
        }
    }

}
