package com.frecciagiovi.business;
import com.frecciagiovi.dto.StazioneDTO;
import com.frecciagiovi.dto.StazioneMapper;
import com.frecciagiovi.model.Stazione;
import com.frecciagiovi.repository.JpaUtil;
import com.frecciagiovi.repository.StazioneRepository;
import com.frecciagiovi.repository.TrenoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class StazioneManager {

    private static final Logger logger = LogManager.getLogger(StazioneManager.class);

    public static StazioneDTO getStazioneById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id non valido");
        }

        var em = JpaUtil.getEntityManager();

        try {
            var stazioneRepo = new StazioneRepository(em);
            var trenoRepo = new TrenoRepository(em);

            // 1) prendo la stazione (ENTITY)
            var stazioneOpt = stazioneRepo.findById(id); // <-- vedi sotto
            if (stazioneOpt.isEmpty()) {
                return null;
            }

            var stazione = stazioneOpt.get();

            // 2) prendo i treni della stazione con query dedicata
            var treni = trenoRepo.findByStazioneId(id);

            // 3) mapping DTO (stazione + treni)
            return StazioneMapper.toDetailDTO(stazione, treni);

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

    public static Stazione updateStazione(Long id, Stazione payload) {

        //Validazioni base
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id non valido");
        }

        if (payload == null) {
            throw new IllegalArgumentException("Payload obbligatorio");
        }

        var em = JpaUtil.getEntityManager();
        var repo = new StazioneRepository(em);

        try {
            em.getTransaction().begin();

            // Carico l'entità reale dal DB
            Stazione existing = repo.findById(id).orElse(null);
            if (existing == null) {
                throw new IllegalStateException("Stazione non trovata: " + id);
            }

            //Aggiorno SOLO i campi consentiti
            if (payload.getNomeStazione() == null || payload.getNomeStazione().isBlank()) {
                throw new IllegalArgumentException("nomeStazione obbligatorio");
            }

            String nuovoNome = payload.getNomeStazione().trim();

            //Controllo duplicati SOLO se il nome cambia
            if (!nuovoNome.equals(existing.getNomeStazione())
                    && repo.existsByNome(nuovoNome)) {
                throw new IllegalStateException(
                        "Esiste già una stazione con nome: " + nuovoNome
                );
            }

            //Apply update
            existing.setNomeStazione(nuovoNome);

            repo.save(existing); // merge implicito

            em.getTransaction().commit();
            logger.info("Stazione aggiornata: id={}, nome={}", id, nuovoNome);

            return existing;

        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.error("Errore aggiornamento stazione: id={}", id, e);
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
