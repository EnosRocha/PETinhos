package com.example.allanimals.domain.repositories;

import com.example.allanimals.domain.model.entities.Favorito;

import java.util.List;
import java.util.UUID;

public interface FavoritoRepository {

    void salvar(Favorito favorito);
    void remover(UUID usuarioId, Long animalId);
    boolean existe(UUID usuarioId, Long animalId);
    List<UUID> listarAnimalIdsPorUsuario(UUID usuarioId);
    Favorito findById(UUID userId);
}
