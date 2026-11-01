package com.example.allanimals.infrastructure.persistence.jpa.impl;

import com.example.allanimals.domain.model.entities.Favorito;
import com.example.allanimals.domain.repositories.FavoritoRepository;
import com.example.allanimals.infrastructure.persistence.jpa.entities.AnimalEntity;
import com.example.allanimals.infrastructure.persistence.jpa.entities.FavoritoEntity;
import com.example.allanimals.infrastructure.persistence.jpa.entities.TutorEntity;
import com.example.allanimals.infrastructure.persistence.jpa.mappers.FavoritoMapperInfra;
import com.example.allanimals.infrastructure.persistence.jpa.mappers.TutorMapperInfra;
import com.example.allanimals.infrastructure.persistence.jpa.respositories.FavoritoInfraRepository;
import com.example.allanimals.infrastructure.persistence.jpa.respositories.TutorInfraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class FavoritoRepositoryImpl implements FavoritoRepository {

    private final FavoritoInfraRepository favoritoInfraRepository;
    private final AnimalRepositoryImpl animalRepository;
    private final TutorRepositoryImpl tutorRepository;
    private final TutorInfraRepository tutorInfraRepository;

    public FavoritoRepositoryImpl(FavoritoInfraRepository favoritoInfraRepository, AnimalRepositoryImpl animalRepository, TutorRepositoryImpl tutorRepository, TutorInfraRepository tutorInfraRepository) {
        this.favoritoInfraRepository = favoritoInfraRepository;
        this.animalRepository = animalRepository;
        this.tutorRepository = tutorRepository;
        this.tutorInfraRepository = tutorInfraRepository;
    }

    @Override
    public void salvar(Favorito favorito) {
        Long animalId = favorito.getAnimalId().stream().findFirst().orElseThrow();

        AnimalEntity animalEntity = animalRepository.getAnimalEntity(animalId);
        TutorEntity tutorEntity = tutorInfraRepository.getReferenceById(favorito.getUsuarioId());

        FavoritoEntity entity = new FavoritoEntity();
        entity.setUsuario(tutorEntity);
        entity.setAnimal(animalEntity);

        favoritoInfraRepository.save(entity);
    }

    @Override
    public void remover(UUID usuarioId, Long animalId) {
        favoritoInfraRepository.deleteByUsuarioIdAndAnimalRegistro(usuarioId, animalId);
    }

    @Override
    public boolean existe(UUID usuarioId, Long animalId) {
        return favoritoInfraRepository.existsByUsuarioIdAndAnimalRegistro(usuarioId, animalId);
    }

    @Override
    public List<UUID> listarAnimalIdsPorUsuario(UUID usuarioId) {
        return List.of();
    }

    @Override
    public Favorito findById(UUID userId) {
        List<FavoritoEntity> entities = favoritoInfraRepository.findByUsuarioId(userId);
        return FavoritoMapperInfra.toDomain(userId, entities);
    }
}