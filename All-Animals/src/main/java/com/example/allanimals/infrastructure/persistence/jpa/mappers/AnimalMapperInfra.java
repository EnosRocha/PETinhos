package com.example.allanimals.infrastructure.persistence.jpa.mappers;

import com.example.allanimals.domain.model.entities.Animal;
import com.example.allanimals.domain.model.objectValue.RegistroAnimal;
import com.example.allanimals.infrastructure.persistence.jpa.entities.AnimalEntity;

public class AnimalMapperInfra {

    public static AnimalEntity toEntity(Animal animal) {
        if (animal == null) return null;

        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setName(animal.getName());
        animalEntity.setImage(animal.getImage());
        animalEntity.setTipoAnimal(animal.getTipoAnimal());
        animalEntity.setCor(animal.getCor());
        animalEntity.setPeso(animal.getPeso());
        animalEntity.setDescricao(animal.getDescricao());
        animalEntity.setEndereco(animal.getEndereco());
        animalEntity.setRaca(animal.getRaca());
        animalEntity.setDonoId(animal.getDonoId());
        animalEntity.setIdade(animal.getIdade());


        return animalEntity;
    }

    public static Animal toDomain(AnimalEntity dto) {
        if (dto == null) return null;

        Animal animal = new Animal();
        animal.setIdade(dto.getIdade());
        animal.setRaca(dto.getRaca());
        animal.setCor(dto.getCor());
        animal.setDescricao(dto.getDescricao());
        animal.setPeso(dto.getPeso());
        animal.setName(dto.getName());
        animal.setImage(dto.getImage());
        animal.setTipoAnimal(dto.getTipoAnimal());
        animal.setIdade(dto.getIdade());
        animal.setRegistro(new RegistroAnimal(dto.getRegistro()));
        animal.setEndereco(dto.getEndereco());

        return animal;
    }
}
