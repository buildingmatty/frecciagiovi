package com.frecciagiovi.dto;

public class StazioneDTO {
    private Long id;
    private String nomeStazione;

    public StazioneDTO() {}

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getNomeStazione(){return nomeStazione;}
    public void setNomeStazione(String nome){this.nomeStazione = nome;}
}
