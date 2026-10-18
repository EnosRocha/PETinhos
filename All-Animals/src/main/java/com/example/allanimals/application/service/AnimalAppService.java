package com.example.allanimals.application.service;

import com.example.allanimals.application.dto.AnimalRequestDto;
import com.example.allanimals.application.dto.AnimalResponseDto;
import com.example.allanimals.application.mappers.AnimalMappersApp;
import com.example.allanimals.domain.model.entities.Animal;
import com.example.allanimals.infrastructure.persistence.jpa.entities.AnimalEntity;
import com.example.allanimals.infrastructure.persistence.jpa.impl.AnimalRepositoryImpl;
import com.example.allanimals.infrastructure.persistence.jpa.mappers.AnimalMapperInfra;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AnimalAppService {

    private final AnimalRepositoryImpl animalRepository;

    public AnimalAppService(AnimalRepositoryImpl animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Long cadastrarAnimal(AnimalRequestDto dto, UUID tutorId) {
        if (dto == null) throw new IllegalArgumentException("Animals information cant be null");
        Animal animal = AnimalMappersApp.toDomain(dto);
        animal.setTutorId(tutorId);
        Long idRetornado = animalRepository.save(animal);
        return idRetornado;
    }

    public void deletarAnimal(Long registro) {
        if (registro == null) throw new IllegalArgumentException("Animals information cant be null");
        animalRepository.delete(registro);

    }

    public List<AnimalResponseDto> getAllAnimals() {
        List<Animal> animals = animalRepository.getAllAnimals();
        List<AnimalResponseDto> response = animals.stream().map(animal -> AnimalMappersApp.toResponse(animal)).collect(Collectors.toUnmodifiableList());
        return response;
    }


    public List<AnimalResponseDto> getAllAnimals(UUID id) {

        System.out.println("Tutor solicitado: " + id);

        List<Animal> animals = animalRepository.getAllAnimals();

        animals.forEach(animal ->
                System.out.println(
                        "Animal: " + animal.getRegistro()
                                + " | Nome: " + animal.getName()
                                + " | Tutor do animal: " + animal.getTutorId()
                )
        );

        List<Animal> animalsFiltrados = animals.stream()
                .filter(animal -> id != null && id.equals(animal.getTutorId()))
                .toList();

        System.out.println("Total antes do filtro: " + animals.size());
        System.out.println("Total depois do filtro: " + animalsFiltrados.size());

        return animalsFiltrados.stream()
                .map(AnimalMappersApp::toResponse)
                .toList();
    }


    public AnimalResponseDto getAnimal(Long id){
       Animal animal = animalRepository.getAnimal(id);
       AnimalResponseDto animalDto = AnimalMappersApp.toResponse(animal);
       return animalDto;
    }
    public AnimalEntity getAnimalForImage(Long id) {
        return animalRepository.getAnimalEntity(id);
    }
}
