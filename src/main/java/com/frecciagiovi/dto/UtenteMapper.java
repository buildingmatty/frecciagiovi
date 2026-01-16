package com.frecciagiovi.dto;

import com.frecciagiovi.model.Utente;

public class UtenteMapper {

    public static UtenteDTO toDTO(Utente u){
        if(u == null) return null;

        UtenteDTO dto = new UtenteDTO();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setCognome(u.getCognome());
        dto.setEmail(u.getEmail());
        dto.setDataNascita(u.getDataNascita());
        return dto;
    }

    public static Utente toEntity(UtenteDTO dto){
        if(dto == null) return null;
        Utente u = new Utente();
        u.setId(dto.getId());
        u.setNome(dto.getNome());
        u.setCognome(dto.getCognome());
        u.setEmail(dto.getEmail());
        u.setDataNascita(dto.getDataNascita());
        return u;
    }
}
