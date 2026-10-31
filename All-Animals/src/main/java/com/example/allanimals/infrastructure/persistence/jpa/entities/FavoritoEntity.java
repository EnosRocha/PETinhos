package com.example.allanimals.infrastructure.persistence.jpa.entities;

import com.example.allanimals.domain.model.entities.Animal;
import com.example.allanimals.domain.model.entities.Tutor;
import jakarta.persistence.*;

@Entity
@Table(name = "favorito",
        uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "animal_id"}))
public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id")
    private Tutor usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "animal_id")
    private Animal animal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tutor getUsuario() {
        return usuario;
    }

    public void setUsuario(Tutor usuario) {
        this.usuario = usuario;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}