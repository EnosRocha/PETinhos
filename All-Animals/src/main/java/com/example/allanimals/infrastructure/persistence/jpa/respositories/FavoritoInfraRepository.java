package com.example.allanimals.infrastructure.persistence.jpa.respositories;

import com.example.allanimals.infrastructure.persistence.jpa.entities.FavoritoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FavoritoInfraRepository extends JpaRepository<FavoritoEntity, Long> {

    void salvar(FavoritoEntity favorito);

    void remover(UUID usuarioId, Long animalId);

    boolean existe(UUID usuarioId, Long animalId);

    List<UUID> listarAnimalIdsPorUsuario(UUID usuarioId);

    FavoritoEntity findById(UUID userId);
}
