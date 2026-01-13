package com.frecciagiovi.repository;

import com.frecciagiovi.model.Utente;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UtenteRepository {

    private EntityManager entityManager;

    public UtenteRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Utente utente) {
        if(utente.getId() == null){
            entityManager.persist(utente);
        }
        else{
            entityManager.merge(utente);
        }
    }

    public Optional<Utente> findById(Long id){
        return Optional.ofNullable(entityManager.find(Utente.class, id));
    }

    public List<Utente> findAll(){
        return entityManager.createQuery(
                """
                 select u
                 from Utente u
                 order by u.cognome, u.nome
                 """, Utente.class)
                .getResultList();
    }

    public void delete(Utente utente){
        Utente managed = entityManager.contains(utente)
                ? utente
                : entityManager.merge(utente);
        entityManager.remove(managed);
    }

    public void deleteById(Long id){
        findById(id).ifPresent(this::delete);
    }

    //Utenti in una stazione
    public List<Utente> findByStazioneNome(String nomeStazione) {
        return entityManager
                .createQuery("""
                    select u
                    from Utente u
                    wher u.stazione.nomeStazione = :nome
                    order by u.cognome, u.nome
                    """, Utente.class)
                .setParameter("nome", nomeStazione)
                .getResultList();
    }

    //Utenti nati prima di una certa data
    public List<Utente> findByNatiPrimaDi(LocalDate data){
        return entityManager
                .createQuery("""
                    select u
                    from Utente u
                    where u.dataNascita < :data
                    order by u.dataNascita
                    """, Utente.class)
                .setParameter("data", data)
                .getResultList();
    }
    //Cerca per email
    public Optional<Utente> findByEmail(String email){
        return entityManager
                .createQuery("""
                    select u
                    from Utente u
                    where u.email = :mail
                    """, Utente.class)
                .setParameter("mail", email)
                .getResultList().stream().findFirst();
    }
    //Controllo esistenza email
    public boolean existByEmail(String email){
        Long count = entityManager
                .createQuery("""
                    select count(u)
                    from Utente u
                    where u.email = :email
                    """,Long.class)
                .setParameter("email", email)
                .getSingleResult();
        return count !=null && count > 0;
    }
}
