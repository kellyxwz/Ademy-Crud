package com.example.academy.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username é obrigatória")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Senha é obrigatória")
    @Column(nullable = false)
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 à 20 caracteres")
    private String password;

    @NotBlank(message = "Role é obrigatória")
    @Pattern(regexp = "^(ALUNO|PROFESSOR)$", message = "Role deve ser ALUNO ou PROFESSOR")
    @Column(nullable = false, length = 20)
    private String role;
    public Usuario() {
    }

    public Usuario(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
