package com.example.allanimals.infrastructure.persistence.jpa.mappers;

import com.example.allanimals.domain.model.entities.Favorito;
import com.example.allanimals.infrastructure.persistence.jpa.entities.AnimalEntity;
import com.example.allanimals.infrastructure.persistence.jpa.entities.FavoritoEntity;
import com.example.allanimals.infrastructure.persistence.jpa.entities.TutorEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class FavoritoMapperInfra {

    public static Favorito toDomain(UUID usuarioId, List<FavoritoEntity> entities) {
        Favorito favorito = new Favorito();
        favorito.setUsuarioId(usuarioId);

        Set<Long> animalIds = entities == null
                ? new HashSet<>()
                : entities.stream()
                .map(e -> e.getAnimal().getRegistro())
                .collect(Collectors.toSet());

        favorito.setAnimalId(animalIds);
        return favorito;
    }

    public static FavoritoEntity toEntity(UUID usuarioId, Long animalId) {
        TutorEntity tutor = new TutorEntity();
        tutor.setId(usuarioId);

        AnimalEntity animal = new AnimalEntity();
        animal.setRegistro(animalId);

        FavoritoEntity entity = new FavoritoEntity();
        entity.setUsuario(tutor);
        entity.setAnimal(animal);
        return entity;
    }

    public static List<FavoritoEntity> toEntities(Favorito favorito) {
        if (favorito == null || favorito.getAnimalId() == null) return List.of();

        return favorito.getAnimalId().stream()
                .map(animalId -> toEntity(favorito.getUsuarioId(), animalId))
                .collect(Collectors.toList());
    }
}