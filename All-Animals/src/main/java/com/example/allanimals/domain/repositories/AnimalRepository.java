package com.example.allanimals.domain.repositories;

import com.example.allanimals.domain.model.entities.Animal;

import java.util.List;

public interface AnimalRepository {

    Long save(Animal animal);
    void delete(Long id);
    Animal update(Animal animal);
    List<Animal> getAllAnimals();
    Animal getAnimal(Long id);
}
