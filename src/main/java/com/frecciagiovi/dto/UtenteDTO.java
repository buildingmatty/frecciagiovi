package com.frecciagiovi.dto;

import java.time.LocalDate;

public class UtenteDTO {

    private Long id;
    private String nome, cognome, email;
    private LocalDate dataNascita;

    public UtenteDTO() {}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getCognome() {return cognome;}
    public void setCognome(String cognome) {this.cognome = cognome;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public LocalDate getDataNascita() {return dataNascita;}
    public void setDataNascita(LocalDate dataNascita) {this.dataNascita = dataNascita;}
}

