package com.frecciagiovi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

public class Treno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinazione;

    private LocalDateTime oraPartenza;

    private String binario;

    private LocalDateTime durataTratta;

    @ManyToOne
    @JoinColumn(name = "stazione_id")
    private Stazione stazione;

    private String numeriTreno;

    public Treno() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public LocalDateTime getOraPartenza() {
        return oraPartenza;
    }

    public void setOraPartenza(LocalDateTime oraPartenza) {
        this.oraPartenza = oraPartenza;
    }

    public String getBinario() {
        return binario;
    }

    public void setBinario(String binario) {
        this.binario = binario;
    }

    public LocalDateTime getDurataTratta() {
        return durataTratta;
    }

    public void setDurataTratta(LocalDateTime durataTratta) {
        this.durataTratta = durataTratta;
    }

    public Stazione getStazione() {
        return stazione;
    }

    public void setStazione(Stazione stazione) {
        this.stazione = stazione;
    }

    public String getNumeriTreno() {
        return numeriTreno;
    }

    public void setNumeriTreno(String numeriTreno) {
        this.numeriTreno = numeriTreno;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Treno treno = (Treno) o;
        return Objects.equals(id, treno.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Treno{" +
                "id=" + id +
                ", destinazione='" + destinazione + '\'' +
                ", oraPartenza=" + oraPartenza +
                ", binario='" + binario + '\'' +
                ", durataTratta=" + durataTratta +
                ", stazione=" + stazione +
                ", numeriTreno='" + numeriTreno + '\'' +
                '}';
    }
}
