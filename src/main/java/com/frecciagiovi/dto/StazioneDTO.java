package com.frecciagiovi.dto;

import java.util.List;

public class StazioneDTO {
    private Long id;
    private String nomeStazione;
    private List<TrenoDTO> treniList;

    public StazioneDTO() {}

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getNomeStazione(){return nomeStazione;}
    public void setNomeStazione(String nome){this.nomeStazione = nome;}

    public List<TrenoDTO> getTreniList(){return treniList;}

    public void setTreniList(List<TrenoDTO> treniList) {this.treniList = treniList;}
}
