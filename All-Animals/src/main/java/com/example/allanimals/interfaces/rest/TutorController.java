package com.example.allanimals.interfaces.rest;


import com.example.allanimals.application.dto.TutorRequestDto;
import com.example.allanimals.application.dto.TutorResponseDto;
import com.example.allanimals.application.service.TutorAppService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tutor")
public class TutorController {


    private final TutorAppService service;

    public TutorController(TutorAppService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid TutorRequestDto dto) {
        service.cadastrarTutor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deleteTutor(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TutorResponseDto> atualizar(@PathVariable UUID id,
                                                      @RequestBody @Valid TutorRequestDto dto) {

        service.updateTutor(id, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TutorResponseDto>> listar() {
        return ResponseEntity.ok(service.getAllTutors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorResponseDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getTutor(id));
    }

    @GetMapping("/me")
    public ResponseEntity<TutorResponseDto> buscarMeuPerfil(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(service.getTutorByEmail(email));
    }
}
