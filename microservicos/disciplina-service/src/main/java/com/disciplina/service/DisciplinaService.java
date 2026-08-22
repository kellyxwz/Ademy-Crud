package com.example.academy.service;

import com.example.academy.dto.reponse.DisciplinaResponseDTO;
import com.example.academy.dto.request.DisciplinaRequestDTO;
import com.example.academy.exceptions.ResourceNotFoundException;
import com.example.academy.model.Disciplina;
import com.example.academy.repository.DisciplinaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    public DisciplinaResponseDTO findById(Long id){
        Disciplina disciplina = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado com o id: " + id));

        return new DisciplinaResponseDTO(disciplina);
    }

    public Page<Disciplina> buscarAvancado(String nome,
                                           Boolean ativo,
                                           int page,
                                           int size,
                                           String sortBy,
                                           String direction) {

        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Disciplina> spec = Specification.unrestricted();

        if (nome != null && !nome.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
        }
        if (ativo != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("ativo"), ativo));
        }

        return repository.findAll(spec, pageable);
    }

    public DisciplinaResponseDTO create(DisciplinaRequestDTO requestDTO){
        Disciplina disciplina = toEntity(requestDTO);
        Disciplina disciplinaSalva = repository.save(disciplina);
        return new DisciplinaResponseDTO(disciplinaSalva);
    }

    public void delete (Long id){
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado com o id: " + id);
        }
        repository.deleteById(id);
    }

    public DisciplinaResponseDTO update(Long id, DisciplinaRequestDTO updateData){
        Disciplina disciplina = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado com o id: " + id));
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
