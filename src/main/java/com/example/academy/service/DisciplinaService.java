package com.example.academy.service;

import com.example.academy.dto.reponse.DisciplinaResponseDTO;
import com.example.academy.dto.request.DisciplinaRequestDTO;
import com.example.academy.model.Disciplina;
import com.example.academy.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    private final DisciplinaRepository repository;

    public DisciplinaService(DisciplinaRepository repository) {
        this.repository = repository;
    }

    public List<DisciplinaResponseDTO> findAll(){
        return repository.findAll().stream().map(DisciplinaResponseDTO :: new).toList();
    }

    public DisciplinaResponseDTO create(DisciplinaRequestDTO requestDTO){
        Disciplina disciplina = toEntity(requestDTO);
        Disciplina disciplinaSalva = repository.save(disciplina);
        return new DisciplinaResponseDTO(disciplinaSalva);
    }

    public void delete (Long id){
        repository.deleteById(id);
    }

    public DisciplinaResponseDTO update(Long id, DisciplinaRequestDTO updateData){
        Disciplina disciplina = repository.findById(id).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
        updateData(disciplina, updateData);
        repository.save(disciplina);

        return new DisciplinaResponseDTO(disciplina);
    }

    private void updateData(Disciplina disciplina, DisciplinaRequestDTO dto){
        disciplina.setNome(dto.nome());
        disciplina.setCargaHoraria(dto.cargaHoraria());

    }

    public static Disciplina toEntity(DisciplinaRequestDTO dto){
       Disciplina disciplina = new Disciplina();
       disciplina.setNome(dto.nome());
       disciplina.setCargaHoraria(dto.cargaHoraria());
       disciplina.setAtivo(true);

       return disciplina;
    }

}
