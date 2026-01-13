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


    /** Cerca una stazione per nome (match esatto) */
    public Optional<Stazione> findByNome(String nomeStazione) {
        List<Stazione> res = entityManager
                .createQuery("""
                    select s
                    from Stazione s
                    where s.nomeStazione = :nome
                """, Stazione.class)
                .setParameter("nome", nomeStazione)
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
                .setParameter("q", "%" + testo + "%")
                .getResultList();
    }

    /** Controllo rapido esistenza (utile per evitare doppio popolamento DB) */
    public boolean existsByNome(String nomeStazione) {
        Long count = entityManager
                .createQuery("""
                    select count(s)
                    from Stazione s
                    where s.nomeStazione = :nome
                """, Long.class)
                .setParameter("nome", nomeStazione)
                .getSingleResult();

        return count != null && count > 0;
    }
}