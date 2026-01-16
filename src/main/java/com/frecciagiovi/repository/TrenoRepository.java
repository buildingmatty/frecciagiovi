package com.frecciagiovi.repository;

import com.frecciagiovi.model.Treno;
import jakarta.persistence.EntityManager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TrenoRepository {

    private final EntityManager entityManager;

    public TrenoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Treno treno) {
        // persist solo se è nuovo (id null), altrimenti merge
        if (treno.getId() == null) {
            entityManager.persist(treno);
        } else {
            entityManager.merge(treno);
        }
    }

    public Optional<Treno> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Treno.class, id));
    }

    public List<Treno> findByStazioneId(Long stazioneId) {
        return entityManager.createQuery(
                        "SELECT t " +
                                "FROM Treno t " +
                                "WHERE t.stazione.id = :id ORDER BY t.oraPartenza",
                        Treno.class
                )
                .setParameter("id", stazioneId)
                .getResultList();
    }


    public List<Treno> findAllOrderByOraPartenza() {
        return entityManager
                .createQuery("""
                    select t
                    from Treno t
                    order by t.oraPartenza
                """, Treno.class)
                .getResultList();
    }

    public void delete(Treno treno) {
        Treno managed = entityManager.contains(treno) ? treno : entityManager.merge(treno);
        entityManager.remove(managed);
    }

    public void deleteById(Long id) {
        findById(id).ifPresent(this::delete);
    }


    /** Tutti i treni che partono da una stazione (per nome), ordinati per orario */
    public List<Treno> findByStazioneNome(String nomeStazione) {
        return entityManager
                .createQuery("""
                    select t
                    from Treno t
                    where t.stazione.nomeStazione = :nome
                    order by t.oraPartenza
                """, Treno.class)
                .setParameter("nome", nomeStazione)
                .getResultList();
    }

    /** Treni in una fascia oraria */
    public List<Treno> findInFasciaOraria(LocalDateTime da, LocalDateTime a) {
        return entityManager
                .createQuery("""
                    select t
                    from Treno t
                    where t.oraPartenza between :da and :a
                    order by t.oraPartenza
                """, Treno.class)
                .setParameter("da", da)
                .setParameter("a", a)
                .getResultList();
    }

    /** Treni con durata maggiore di una soglia */
    public List<Treno> findByDurataMaggioreDi(Duration min) {
        return entityManager
                .createQuery("""
                    select t
                    from Treno t
                    where t.durataTratta > :min
                    order by t.durataTratta desc
                """, Treno.class)
                .setParameter("min", min)
                .getResultList();
    }

    /** Treni tra partenza e destinazione */
    public List<Treno> findByPartenzaEDestinazione(String partenzaNome, String destinazioneNome) {
        return entityManager
                .createQuery("""
                    select t
                    from Treno t
                    where t.stazione.nomeStazione = :partenza
                      and t.destinazione = :dest
                    order by t.oraPartenza
                """, Treno.class)
                .setParameter("partenza", partenzaNome)
                .setParameter("dest", destinazioneNome)
                .getResultList();
    }
}
