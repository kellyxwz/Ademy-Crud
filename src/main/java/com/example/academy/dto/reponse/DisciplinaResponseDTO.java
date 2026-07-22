package com.example.academy.dto.reponse;

import com.example.academy.model.Disciplina;

public record DisciplinaResponseDTO(
        String id,
        String name,
        Integer cargaHoraria
) {

    public DisciplinaResponseDTO(Disciplina disciplina){
        this(
            disciplina.getId(),
            disciplina.getNome(),
            disciplina.getCargaHoraria()
        );
    }
}
