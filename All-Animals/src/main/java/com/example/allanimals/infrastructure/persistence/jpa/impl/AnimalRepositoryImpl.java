package com.example.allanimals.infrastructure.persistence.jpa.impl;

import com.example.allanimals.domain.model.entities.Animal;
import com.example.allanimals.domain.repositories.AnimalRepository;
import com.example.allanimals.infrastructure.persistence.jpa.entities.AnimalEntity;
import com.example.allanimals.infrastructure.persistence.jpa.mappers.AnimalMapperInfra;
import com.example.allanimals.infrastructure.persistence.jpa.respositories.AnimalInfraRepository;
import com.example.allanimals.infrastructure.persistence.jpa.respositories.TutorInfraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AnimalRepositoryImpl implements AnimalRepository {

    private final AnimalInfraRepository animalInfraRepositor;
    private final TutorInfraRepository tutorInfraRepository;



    public AnimalRepositoryImpl(AnimalInfraRepository animalInfraRepositor, TutorInfraRepository tutorInfraRepository) {
        this.animalInfraRepositor = animalInfraRepositor;
        this.tutorInfraRepository = tutorInfraRepository;
    }

    @Override
    public Long save(Animal animal) {
        AnimalEntity entity = AnimalMapperInfra.toEntity(animal);
        if (animal.getTutorId() != null) {
            tutorInfraRepository.findById(animal.getTutorId())
                    .ifPresent(entity::setTutor);
        }
        AnimalEntity saved = animalInfraRepositor.save(entity);
        return saved.getRegistro();
    }

    @Override
    public void delete(Long id) {
        animalInfraRepositor.deleteById(id);
    }

    @Override
    public Animal update(Animal animal) {
        AnimalEntity entity = AnimalMapperInfra.toEntity(animal);
        AnimalEntity saved = animalInfraRepositor.save(entity);
        return AnimalMapperInfra.toDomain(saved);
    }

    @Override
    public List<Animal> getAllAnimals() {
        return animalInfraRepositor.findAll()
                .stream()
                .map(animal -> AnimalMapperInfra.toDomain(animal))
                .collect(Collectors.toList());
    }

    @Override
    public Animal getAnimal(Long id) {
        AnimalEntity animalEntity = animalInfraRepositor.findById(id).get();
        Animal animal = AnimalMapperInfra.toDomain(animalEntity);
        return animal;
    }

    public AnimalEntity getAnimalEntity(Long id) {
        return animalInfraRepositor.findById(id).get();
    }
}