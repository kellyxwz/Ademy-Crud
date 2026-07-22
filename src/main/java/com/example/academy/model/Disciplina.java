package com.example.academy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "disciplina")
public class Disciplina {

    @Id
    private String id;
    private String nome;
    private Integer cargaHoraria;
    private Boolean ativo;

    public Disciplina(){}

    public Disciplina(String id, String nome, Integer cargaHoraria, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.ativo = ativo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
