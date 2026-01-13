package com.frecciagiovi.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stazione")

public class Stazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeStazione;

    @OneToMany(mappedBy = "stazione")
    private List<Utente> utentiList;

    @OneToMany(mappedBy = "stazione")
    private List<Treno> trenoList;

    public Stazione() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeStazione() {
        return nomeStazione;
    }

    public void setNomeStazione(String nomeStazione) {
        this.nomeStazione = nomeStazione;
    }

    public List<Utente> getUtentiList() {
        return utentiList;
    }

    public void setUtentiList(List<Utente> utentiList) {
        this.utentiList = utentiList;
    }

    public List<Treno> getTrenoList() {
        return trenoList;
    }

    public void setTrenoList(List<Treno> trenoList) {
        this.trenoList = trenoList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Stazione stazione = (Stazione) o;
        return Objects.equals(id, stazione.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Stazione{" +
                "id=" + id +
                ", nomeStazione='" + nomeStazione + '\'' +
                ", utentiList=" + utentiList +
                ", trenoList=" + trenoList +
                '}';
    }
}