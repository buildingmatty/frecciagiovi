package com.frecciagiovi.dto;

import java.time.Duration;
import java.time.LocalDateTime;

public class TrenoDTO {
    private Long id;
    private LocalDateTime oraPartenza;
    private String binario;
    private Duration durataTratta;

    public TrenoDTO() {}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public LocalDateTime getOraPartenza() {return oraPartenza;}
    public void setOraPartenza(LocalDateTime oraPartenza){this.oraPartenza = oraPartenza;}

    public String getBinario() {return binario;}
    public void setBinario(String binario) {this.binario = binario;}

    public Duration getDurataTratta() {return durataTratta;}
    public void setDurataTratta(Duration durataTratta) {this.durataTratta = durataTratta;}

}
