package com.example.allanimals.infrastructure.persistence.jpa.impl;

import com.example.allanimals.domain.model.entities.Favorito;
import com.example.allanimals.domain.repositories.FavoritoRepository;
import com.example.allanimals.infrastructure.persistence.jpa.respositories.FavoritoInfraRepository;

import java.util.List;
import java.util.UUID;

public class FavoritoRepositoryImpl implements FavoritoRepository {

private final FavoritoInfraRepository favoritoInfraRepository;

    public FavoritoRepositoryImpl(FavoritoInfraRepository favoritoInfraRepository) {
        this.favoritoInfraRepository = favoritoInfraRepository;
    }

    @Override
    public void salvar(Favorito favorito) {

    }

    @Override
    public void remover(UUID usuarioId, Long animalId) {

    }

    @Override
    public boolean existe(UUID usuarioId, Long animalId) {
        return false;
    }

    @Override
    public List<UUID> listarAnimalIdsPorUsuario(UUID usuarioId) {
        return List.of();
    }

    @Override
    public Favorito findById(UUID userId) {
        return null;
    }
}
