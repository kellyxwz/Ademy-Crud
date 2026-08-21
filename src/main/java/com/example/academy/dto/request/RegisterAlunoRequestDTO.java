package com.example.academy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterAlunoRequestDTO(

        @NotBlank
        @Size(min = 3, max = 120)
        String username,

        @NotBlank
        @Size(min = 6, max = 20)
        String password

) {}