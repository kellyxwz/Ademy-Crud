package com.example.academy.service;

import com.example.academy.dto.reponse.ProfessorResponseDTO;
import com.example.academy.dto.request.ProfessorRequestDTO;
import com.example.academy.model.Professor;
import com.example.academy.repository.ProfessorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Professor> buscaAvancada (String nome,
                                          String email,
                                          String especialidade,
                                          Integer idadeMin,
                                          Integer idadeMax,
                                          Boolean ativo,
                                          int page,
                                          int size,
                                          String sortBy,
                                          String direction){

        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();  //aqui monta a ordenação se vai ser crescente ou descrescente
        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Professor> spec = Specification.unrestricted();  //

        //define os filtros de busca e adiciona aos containers aquiiiii
        if (nome != null && !nome.isBlank()){
            spec = spec.and(((root, query, cb) -> cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%")));
        }
        if (email != null && !email.isBlank()){
            spec = spec.and(((root, query, cb) -> cb.like(cb.lower(root.get("email")),  "%" + email.toLowerCase() + "%" )));
        }
        if (especialidade != null && !especialidade.isBlank()){
            spec = spec.and(((root, query, cb) -> cb.like(cb.lower(root.get("especialidade")), "%" + especialidade.toLowerCase() + "%")));
        }
        if (idadeMax != null){
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("idade"), idadeMin));
        }
        if (idadeMin != null){
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("idade"), idadeMax));
        }
        if (ativo != null){
            spec = spec.and((root, query, cb) -> cb.equal(root.get("ativo"), ativo));
        }

        return professorRepository.findAll(spec, pageable);
    }

    public ProfessorResponseDTO create (ProfessorRequestDTO dto){
        Professor professor = toEntity(dto);
        professorRepository.save(professor);
        return new ProfessorResponseDTO(professor);
    }

    public void delete (Long id){
        professorRepository.deleteById(id);
    }

    public ProfessorResponseDTO update(Long id, ProfessorRequestDTO updateData){
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
