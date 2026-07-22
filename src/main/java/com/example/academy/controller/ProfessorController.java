package com.example.academy.controller;

import com.example.academy.dto.reponse.ProfessorResponseDTO;
import com.example.academy.dto.request.ProfessorRequestDTO;
import com.example.academy.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
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
