package com.frecciagiovi.dto;

import com.frecciagiovi.model.Treno;

public class TrenoMapper {

    public static TrenoDTO toDTO(Treno t){
        if(t==null){return null;}

        TrenoDTO dto = new TrenoDTO();
        dto.setId(t.getId());
        dto.setBinario(t.getBinario());
        dto.setOraPartenza(t.getOraPartenza());
        dto.setDurataTratta(t.getDurataTratta());
        return dto;
    }

    public static Treno toEntity(TrenoDTO dto){
        if(dto==null){return null;}
        Treno t = new Treno();
        t.setId(dto.getId());
        t.setBinario(dto.getBinario());
        t.setOraPartenza(dto.getOraPartenza());
        t.setDurataTratta(dto.getDurataTratta());
        return t;
    }
}
