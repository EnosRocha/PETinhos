package com.example.allanimals.interfaces.rest;

import com.example.allanimals.application.dto.FavoritoRequestDto;
import com.example.allanimals.application.service.FavoritoAppService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    private final FavoritoAppService favoritoAppService;

    public FavoritoController(FavoritoAppService favoritoAppService) {
        this.favoritoAppService = favoritoAppService;
    }

    @PostMapping
    public ResponseEntity<Void> favoritar(@RequestBody @Valid FavoritoRequestDto dto) {
        favoritoAppService.favoritar(dto.tutorid(), dto.animalId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{tutorId}/{animalId}")
    public ResponseEntity<Void> desfavoritar(@PathVariable UUID tutorId,
                                             @PathVariable Long animalId) {
        favoritoAppService.desfavoritar(tutorId, animalId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{tutorId}")
    public ResponseEntity<Set<Long>> listar(@PathVariable UUID tutorId) {
        Set<Long> favoritos = favoritoAppService.listar(tutorId);
        return ResponseEntity.ok(favoritos);
    }
}