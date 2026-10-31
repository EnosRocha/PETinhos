package com.example.allanimals.domain.model.entities;

import java.util.Set;
import java.util.UUID;

public class Favorito {
    private Long id;
    private UUID usuarioId;
    private Set<Long> animalId;

    public Favorito() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Set<Long> getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Set<Long> animalId) {
        this.animalId = animalId;
    }
}