package com.example.academy.controller;

import com.example.academy.dto.reponse.DisciplinaResponseDTO;
import com.example.academy.dto.request.DisciplinaRequestDTO;
import com.example.academy.service.DisciplinaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaService service;

    public DisciplinaController(DisciplinaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaResponseDTO>> findAll (){
        List<DisciplinaResponseDTO> list = service.findAll();

        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<DisciplinaResponseDTO> create (@RequestBody DisciplinaRequestDTO requestDTO){
        DisciplinaResponseDTO disciplina = service.create(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(disciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> update (@PathVariable String id, @RequestBody DisciplinaRequestDTO requestDTO){
        DisciplinaResponseDTO disciplina = service.update(id, requestDTO);
        return ResponseEntity.ok(disciplina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable String id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
