package com.frecciagiovi.dao;

import com.frecciagiovi.model.Stazione;
import jakarta.persistence.EntityManager;

import java.util.List;

public class StazioneDao extends DaoUtil {

    private EntityManager em;

    public StazioneDao() { em= DaoUtil.getEntityManager();}

    public Stazione getStazioneById(Long id) {
        return em.find(Stazione.class, id);
    }

    public List<Stazione> getAllStazioni() {
        return em.createQuery("""
                select t
                from com.frecciagiovi.model.Stazione t
                order by t.id
                """, Stazione.class)
                .getResultList();
    }

    public void close(){ em.close(); }

}
