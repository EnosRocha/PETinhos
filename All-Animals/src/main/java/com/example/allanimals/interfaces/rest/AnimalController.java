package com.example.allanimals.interfaces.rest;

import com.example.allanimals.application.dto.AnimalRequestDto;
import com.example.allanimals.application.dto.AnimalResponseDto;
import com.example.allanimals.application.mappers.AnimalMappersApp;
import com.example.allanimals.application.service.AnimalAppService;
import com.example.allanimals.application.service.CloudinaryService;
import com.example.allanimals.domain.model.entities.Tutor;
import com.example.allanimals.domain.model.objectValue.Email;
import com.example.allanimals.domain.repositories.TutorRepository;
import com.example.allanimals.infrastructure.persistence.jpa.entities.AnimalEntity;
import com.example.allanimals.infrastructure.persistence.jpa.entities.AnimalImagemEntity;
import com.example.allanimals.infrastructure.persistence.jpa.mappers.AnimalMapperInfra;
import com.example.allanimals.infrastructure.persistence.jpa.respositories.AnimalImageRepository;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalAppService service;
    private final CloudinaryService cloudinaryService;
    private final AnimalImageRepository imagemRepository;
    private final TutorRepository tutorRepository;


    public AnimalController(AnimalAppService service, CloudinaryService cloudinaryService, AnimalImageRepository imagemRepository, TutorRepository tutorRepository) {
        this.service = service;
        this.cloudinaryService = cloudinaryService;
        this.imagemRepository = imagemRepository;
        this.tutorRepository = tutorRepository;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> cadastrar(
            @RequestPart("animal") @Valid AnimalRequestDto dto,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Tutor tutor = tutorRepository.findByEmail(new Email(email));
        Long animalId = service.cadastrarAnimal(dto, tutor.getTutorId());

        if (images != null && !images.isEmpty()) {
            List<String> urls = cloudinaryService.uploadImagens(images);
            AnimalEntity animalEncontrado = service.getAnimalForImage(animalId);

            List<AnimalImagemEntity> imagens = new ArrayList<>();
            for (int i = 0; i < urls.size(); i++) {
                AnimalImagemEntity imagem = new AnimalImagemEntity();
                imagem.setAnimal(animalEncontrado);
                imagem.setUrl(urls.get(i));
                imagem.setOrdem(i);
                imagens.add(imagem);
            }
            imagemRepository.saveAll(imagens);
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarAnimal(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<Void> finalizar(@PathVariable Long id) {
        service.finalizarAdocao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AnimalResponseDto>> listar() {
        return ResponseEntity.ok(service.getAllAnimals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponseDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAnimal(id));
    }

    @GetMapping("/tutor/{id}")
    public ResponseEntity<List<AnimalResponseDto>> buscarTodosOsAnimaisEmDoacaoDeUmTutor(@PathVariable UUID id){
        return ResponseEntity.ok(service.getAllAnimals(id));
    }



}