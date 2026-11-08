package com.example.allanimals.interfaces.rest;

import com.example.allanimals.application.dto.FavoritoRequestDto;
import com.example.allanimals.application.dto.MessageResponseDto;
import com.example.allanimals.application.service.FavoritoAppService;
import com.example.allanimals.domain.exceptions.FavoritoException;
import com.example.allanimals.domain.model.entities.Animal;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @ExceptionHandler(FavoritoException.class)
    public ResponseEntity<MessageResponseDto> tratarFavorito(FavoritoException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponseDto(exception.getMessage()));
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

    @GetMapping("/{tutorId}/animais")
    public ResponseEntity<List<Animal>> listarAnimais(@PathVariable UUID tutorId) {
        return ResponseEntity.ok(favoritoAppService.listarAnimais(tutorId));
    }
}