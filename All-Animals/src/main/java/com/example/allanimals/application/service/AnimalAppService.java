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
import java.util.stream.Collectors;

@Service
public class AnimalAppService {

    private final AnimalRepositoryImpl animalRepository;

    public AnimalAppService(AnimalRepositoryImpl animalRepository) {
        this.animalRepository = animalRepository;
    }

    public void cadastrarAnimal(AnimalRequestDto dto) {
        if (dto == null) throw new IllegalArgumentException("Animals information cant be null");
        Animal animal = AnimalMappersApp.toDomain(dto);
        animalRepository.save(animal);
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

    public AnimalResponseDto getAnimal(Long id){
       Animal animal = animalRepository.getAnimal(id);
       AnimalResponseDto animalDto = AnimalMappersApp.toResponse(animal);
       return animalDto;
    }
    public AnimalEntity getAnimalForImage(Long id){
       Animal animal = animalRepository.getAnimal(id);
       AnimalEntity animalEntity = AnimalMapperInfra.toEntity(animal);
       return animalEntity;
    }
}
