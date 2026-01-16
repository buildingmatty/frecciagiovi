package com.frecciagiovi.repository;

import com.frecciagiovi.model.Stazione;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class StazioneRepository {

    private final EntityManager entityManager;

    public StazioneRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // -------------------------
    // CRUD base
    // -------------------------

    public void save(Stazione stazione) {
        if (stazione.getId() == null) {
            entityManager.persist(stazione);
        } else {
            entityManager.merge(stazione);
        }
    }

    public Optional<Stazione> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Stazione.class, id));
    }

    public List<Stazione> findAllOrderByNome() {
        return entityManager
                .createQuery("""
                    select s
                    from Stazione s
                    order by s.nomeStazione
                """, Stazione.class)
                .getResultList();
    }

    public void delete(Stazione stazione) {
        Stazione managed = entityManager.contains(stazione) ? stazione : entityManager.merge(stazione);
        entityManager.remove(managed);
    }

    public void deleteById(Long id) {
        findById(id).ifPresent(this::delete);
    }

    // -------------------------
    // Query utili
    // -------------------------

    /** Cerca una stazione per nome (match esatto, case-sensitive) */
    public Optional<Stazione> findByNome(String nomeStazione) {
        List<Stazione> res = entityManager
                .createQuery("""
                    select s
                    from Stazione s
                    where s.nomeStazione = :nome
                """, Stazione.class)
                .setParameter("nome", normalize(nomeStazione))
                .getResultList();

        return res.stream().findFirst();
    }

    /** Cerca una stazione per nome (match esatto, case-insensitive) */
    public Optional<Stazione> findByNomeIgnoreCase(String nomeStazione) {
        List<Stazione> res = entityManager
                .createQuery("""
                    select s
                    from Stazione s
                    where lower(s.nomeStazione) = lower(:nome)
                """, Stazione.class)
                .setParameter("nome", normalize(nomeStazione))
                .getResultList();

        return res.stream().findFirst();
    }

    /** Cerca stazioni che contengono un pezzo di testo (case-insensitive) */
    public List<Stazione> searchByNomeContiene(String testo) {
        return entityManager
                .createQuery("""
                    select s
                    from Stazione s
                    where lower(s.nomeStazione) like lower(:q)
                    order by s.nomeStazione
                """, Stazione.class)
                .setParameter("q", "%" + normalize(testo) + "%")
                .getResultList();
    }

    /** Controllo rapido esistenza per nome (case-insensitive) */
    public boolean existsByNome(String nomeStazione) {
        Long count = entityManager
                .createQuery("""
                    select count(s)
                    from Stazione s
                    where lower(s.nomeStazione) = lower(:nome)
                """, Long.class)
                .setParameter("nome", normalize(nomeStazione))
                .getSingleResult();

        return count != null && count > 0;
    }

    /** Utile per UPDATE: esiste lo stesso nome su un'altra riga? (case-insensitive) */
    public boolean existsByNomeEscludendoId(String nomeStazione, Long idDaEscludere) {
        Long count = entityManager
                .createQuery("""
                    select count(s)
                    from Stazione s
                    where lower(s.nomeStazione) = lower(:nome)
                      and s.id <> :id
                """, Long.class)
                .setParameter("nome", normalize(nomeStazione))
                .setParameter("id", idDaEscludere)
                .getSingleResult();

        return count != null && count > 0;
    }

    /** Conteggio treni collegati (per bloccare delete) */
    public long countTreniByStazioneId(Long stazioneId) {
        Long count = entityManager
                .createQuery("""
                    select count(t)
                    from Treno t
                    where t.stazione.id = :id
                """, Long.class)
                .setParameter("id", stazioneId)
                .getSingleResult();

        return count == null ? 0L : count;
    }

    /** Conteggio utenti collegati (per bloccare delete) */
    public long countUtentiByStazioneId(Long stazioneId) {
        Long count = entityManager
                .createQuery("""
                    select count(u)
                    from Utente u
                    where u.stazione.id = :id
                """, Long.class)
                .setParameter("id", stazioneId)
                .getSingleResult();

        return count == null ? 0L : count;
    }

    // -------------------------
    // Helpers
    // -------------------------

    private static String normalize(String s) {
        return s == null ? null : s.trim();
    }
}
