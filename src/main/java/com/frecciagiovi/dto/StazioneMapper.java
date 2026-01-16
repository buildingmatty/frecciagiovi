package com.frecciagiovi.dto;

import com.frecciagiovi.model.Stazione;
import com.frecciagiovi.model.Treno;

import java.util.List;
import java.util.stream.Collectors;

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

    public static StazioneDTO toDetailDTO(Stazione s, List<Treno> treniList) {
        if (s == null) {
            return null;
        }

        StazioneDTO dto = new StazioneDTO();
        dto.setId(s.getId());
        dto.setNomeStazione(s.getNomeStazione());

        dto.setTreniList(
                treniList == null
                        ? java.util.List.of()
                        : treniList.stream().map(TrenoMapper::toDTO).toList()
        );

        return dto;
    }

}
