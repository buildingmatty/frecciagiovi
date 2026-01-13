package com.frecciagiovi.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "treno")
public class Treno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime oraPartenza;

    private String binario;

    private Duration durataTratta;

    @ManyToOne
    @JoinColumn( name = "destinazione_id")
    private Stazione destinazione;

    @ManyToOne
    @JoinColumn(name = "stazione_id")
    private Stazione stazione;

    private String numeroTreno;

    public Treno() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stazione getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(Stazione destinazione) {
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

    public Duration getDurataTratta() {
        return durataTratta;
    }

    public void setDurataTratta(Duration durataTratta) {
        this.durataTratta = durataTratta;
    }

    public Stazione getStazione() {
        return stazione;
    }

    public void setStazione(Stazione stazione) {
        this.stazione = stazione;
    }

    public String getNumeroTreno() {
        return numeroTreno;
    }

    public void setNumeroTreno(String numeroTreno) {
        this.numeroTreno = numeroTreno;
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
                ", numeroTreno='" + numeroTreno + '\'' +
                '}';
    }
}
