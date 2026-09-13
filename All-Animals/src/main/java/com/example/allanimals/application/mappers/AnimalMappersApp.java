package com.example.allanimals.application.mappers;

import com.example.allanimals.application.dto.AnimalRequestDto;
import com.example.allanimals.application.dto.AnimalResponseDto;
import com.example.allanimals.domain.model.entities.Animal;
import com.example.allanimals.infrastructure.persistence.jpa.entities.AnimalEntity;

public class AnimalMappersApp {

    public static Animal toDomain(AnimalRequestDto dto) {
        if (dto == null) return null;

        Animal animal = new Animal();

        animal.setName(dto.name());
        animal.setImage(dto.image());
        animal.setTipoAnimal(dto.tipoAnimal());
        animal.setRaca(dto.raca());
        animal.setPeso(dto.peso());
        animal.setCor(dto.cor());
        animal.setIdade(dto.idade());
        animal.setDonoId(dto.donoId());
        animal.setDescricao(dto.descricao());
        animal.setEndereco(dto.endereco());


        return animal;
    }

    public static AnimalResponseDto toResponse(Animal animal) {
        if (animal == null) return null;

        return new AnimalResponseDto(
                animal.getRegistro() != null ? animal.getRegistro().getRegistro() : null,
                animal.getName(),
                animal.getImage(),
                animal.getTipoAnimal(),
                animal.getRaca(),
                animal.getPeso(),
                animal.getCor(),
                animal.getIdade(),
                animal.getDonoId(),
                animal.getDescricao(),
                animal.getEndereco()
        );
    }
}