package com.example.academy.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DisciplinaRequestDTO(

        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 120, message = "O nome da disciplina deve ter entre 3 a 120 caracteres")
        String nome,

        @NotBlank(message = "A carga horária é obrigatória")
        @Min(value = 1, message = "A carga horária deve ser maior q 1")
        Integer cargaHoraria
) {
}
