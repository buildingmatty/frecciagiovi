package com.frecciagiovi.dto;

import com.frecciagiovi.model.Stazione;

public class StazioneMapper {

    public static StazioneDTO toDTO(Stazione s){
        if(s==null){
            return null;
        }
        StazioneDTO dto = new StazioneDTO();
        dto.setId(s.getId());
        dto.setNomeStazione(s.getNomeStazione());
        return dto;
    }

    //ID del DTO viene ignorato lato server
    public static Stazione toEntity(StazioneDTO dto){
        if(dto==null){
            return null;
        }
        Stazione s = new Stazione();
        s.setNomeStazione(dto.getNomeStazione());
        return s;
    }
}
