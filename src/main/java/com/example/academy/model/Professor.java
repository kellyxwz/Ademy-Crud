package com.example.academy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public class Professor {

    @Id
    private String id;

    private String nome;
    private String especialidade;
    private String email;
    private Boolean active;

    public Professor (){}

    public Professor(String id, String nome, String especialidade, String email, Boolean active) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.email = email;
        this.active = active;
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

    public void setNome(String name) {
        this.nome = name;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
