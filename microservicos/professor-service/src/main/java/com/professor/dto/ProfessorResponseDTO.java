package com.professor.dto;

import com.professor.model.Professor;

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