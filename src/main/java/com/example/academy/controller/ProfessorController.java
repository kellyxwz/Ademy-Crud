package com.example.academy.controller;

import com.example.academy.dto.reponse.ProfessorResponseDTO;
import com.example.academy.dto.request.ProfessorRequestDTO;
import com.example.academy.model.Professor;
import com.example.academy.service.ProfessorService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> findAll(){
        List<ProfessorResponseDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/busca")
    public Page<Professor> buscaAvancada (@RequestParam(required = false) String nome,
                                          @RequestParam(required = false) String email,
                                          @RequestParam(required = false) String especialidade,
                                          @RequestParam(required = false)  Integer idadeMin,
                                          @RequestParam(required = false) Integer idadeMax,
                                          @RequestParam(required = false) Boolean ativo,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "2") int size,
                                          @RequestParam(defaultValue = "id") String sortBy,
                                          @RequestParam(defaultValue = "asc") String direction){

        return service.buscaAvancada(nome, email, especialidade, idadeMin, idadeMax, ativo, page, size, sortBy, direction);
    }

    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> create(@RequestBody ProfessorRequestDTO professorRequestDTO){
        ProfessorResponseDTO professor = service.create(professorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> update (@PathVariable Long id, @RequestBody ProfessorRequestDTO requestDTO){
        ProfessorResponseDTO professor = service.update(id, requestDTO);

        return ResponseEntity.ok(professor);
    }

}
