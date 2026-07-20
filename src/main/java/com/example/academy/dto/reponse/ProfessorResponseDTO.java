package com.example.academy.dto.reponse;

import com.example.academy.model.Professor;

public record ProfessorResponseDTO (
        String id,
        String name,
        String especialidade,
        String email
)

{
    public ProfessorResponseDTO(Professor professor) {
        this(
                professor.getId(),
                professor.getName(),
                professor.getEspecialidade(),
                professor.getEmail()
        );
    }
}
