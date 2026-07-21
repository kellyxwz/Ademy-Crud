package com.example.academy.service;

import com.example.academy.dto.reponse.ProfessorResponseDTO;
import com.example.academy.dto.request.ProfessorRequestDTO;
import com.example.academy.model.Professor;
import com.example.academy.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<ProfessorResponseDTO> findAll(){
        return professorRepository.findAll().stream().map(ProfessorResponseDTO :: new).toList();
    }

    public ProfessorResponseDTO create (ProfessorRequestDTO dto){
        Professor professor = toEntity(dto);
        professorRepository.save(professor);
        return new ProfessorResponseDTO(professor);
    }

    public void delete (String id){
        professorRepository.deleteById(id);
    }

    public ProfessorResponseDTO update(String id, ProfessorRequestDTO updateData){
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new RuntimeException("usuário não encontrado"));

        updateData(professor, updateData);

        Professor newProfessor = professorRepository.save(professor);

        return new ProfessorResponseDTO(newProfessor);
    }

    private void updateData(Professor professor,ProfessorRequestDTO requestDTO){
        professor.setNome(requestDTO.nome());
        professor.setEspecialidade(requestDTO.especialidade());
        professor.setEmail(requestDTO.email());

    }

    public static Professor toEntity(ProfessorRequestDTO dto){
        Professor professor = new Professor();
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setEspecialidade(dto.especialidade());
        professor.setActive(true);

        return professor;
    }

}
