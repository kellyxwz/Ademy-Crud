package com.example.academy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank(message = "O username é obrigatório")
        @Size( min = 3, max = 120)
        String username,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, max = 20)
        String password,

        @NotBlank(message = "Role é obrigatória")
        @Pattern(regexp = "^(ALUNO|PROFESSOR)$", message = "Role deve ser ALUNO ou PROFESSOR")
        String role

) {
}
