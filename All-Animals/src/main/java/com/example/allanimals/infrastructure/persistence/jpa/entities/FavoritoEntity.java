package com.example.allanimals.infrastructure.persistence.jpa.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "favorito",
        uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "animal_id"}))
public class FavoritoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id")
    private TutorEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "animal_id")
    private AnimalEntity animal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TutorEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(TutorEntity usuario) {
        this.usuario = usuario;
    }

    public AnimalEntity getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalEntity animal) {
        this.animal = animal;
    }
}