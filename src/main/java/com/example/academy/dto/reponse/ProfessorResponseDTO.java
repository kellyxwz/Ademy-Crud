package com.example.academy.dto.reponse;

import com.example.academy.model.Professor;

public record ProfessorResponseDTO (
        Long id,
        String nome,
        String especialidade,
        String email
)

{
    public ProfessorResponseDTO(Professor professor) {
        this(
                professor.getId(),
                professor.getNome(),
                professor.getEspecialidade(),
                professor.getEmail()
        );
    }
}
