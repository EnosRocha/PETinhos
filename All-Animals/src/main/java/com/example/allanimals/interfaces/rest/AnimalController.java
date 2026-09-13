package com.example.allanimals.interfaces.rest;

import com.example.allanimals.application.dto.AnimalRequestDto;
import com.example.allanimals.application.dto.AnimalResponseDto;
import com.example.allanimals.application.mappers.AnimalMappersApp;
import com.example.allanimals.application.service.AnimalAppService;
import com.example.allanimals.application.service.CloudinaryService;
import com.example.allanimals.infrastructure.persistence.jpa.entities.AnimalEntity;
import com.example.allanimals.infrastructure.persistence.jpa.entities.AnimalImagemEntity;
import com.example.allanimals.infrastructure.persistence.jpa.mappers.AnimalMapperInfra;
import com.example.allanimals.infrastructure.persistence.jpa.respositories.AnimalImageRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalAppService service;
    private final CloudinaryService cloudinaryService;
    private final AnimalImageRepository imagemRepository;


    public AnimalController(AnimalAppService service, CloudinaryService cloudinaryService, AnimalImageRepository imagemRepository) {
        this.service = service;
        this.cloudinaryService = cloudinaryService;
        this.imagemRepository = imagemRepository;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid AnimalRequestDto dto) {
        service.cadastrarAnimal(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarAnimal(id);
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


    @PostMapping("/{id}/imagens")
    public ResponseEntity<List<String>> uploadImagens(
            @PathVariable Long id,
            @RequestParam("files") List<MultipartFile> files
    ) {
        List<String> urls = cloudinaryService.uploadImagens(files);

        AnimalEntity animalEcontrado = service.getAnimalForImage(id);


        List<AnimalImagemEntity> imagens = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
            AnimalImagemEntity imagem = new AnimalImagemEntity();
            imagem.setAnimal(animalEcontrado);
            imagem.setUrl(urls.get(i));
            imagem.setOrdem(i);
            imagens.add(imagem);
        }

        imagemRepository.saveAll(imagens);
        return ResponseEntity.ok(urls);
    }
}