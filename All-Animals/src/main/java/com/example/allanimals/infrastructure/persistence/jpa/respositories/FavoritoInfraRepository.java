package com.example.allanimals.infrastructure.persistence.jpa.respositories;

import com.example.allanimals.infrastructure.persistence.jpa.entities.FavoritoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FavoritoInfraRepository extends JpaRepository<FavoritoEntity, Long> {

    List<FavoritoEntity> findByUsuarioId(UUID usuarioId);
    void deleteByUsuarioIdAndAnimalRegistro(UUID usuarioId, Long animalId);

    @Query("SELECT COUNT(f) > 0 FROM FavoritoEntity f WHERE f.usuario.id = :usuarioId AND f.animal.registro = :animalId")
    boolean existsByUsuarioIdAndAnimalRegistro(@Param("usuarioId") UUID usuarioId, @Param("animalId") Long animalId);

}
