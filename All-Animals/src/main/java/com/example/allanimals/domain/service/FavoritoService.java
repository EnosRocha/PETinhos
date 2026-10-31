package com.example.allanimals.domain.service;

import com.example.allanimals.domain.model.entities.Favorito;
import com.example.allanimals.domain.repositories.AnimalRepository;
import com.example.allanimals.domain.repositories.FavoritoRepository;
import com.example.allanimals.domain.repositories.TutorRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final AnimalRepository animalRepository;
    private final TutorRepository tutorRepository;

    public FavoritoService(FavoritoRepository favoritoRepository, AnimalRepository animalRepository, TutorRepository tutorRepository) {
        this.favoritoRepository = favoritoRepository;
        this.animalRepository = animalRepository;
        this.tutorRepository = tutorRepository;
    }

    public void atribuirAnimalFavoritoAoTutor(UUID tutorId, Long petId) {
        Favorito favorito = favoritoRepository.findById(tutorId);

        if (favorito == null) {
            favorito = new Favorito();
            favorito.setUsuarioId(tutorId);
            favorito.setAnimalId(new HashSet<>());
        }

        favorito.getAnimalId().add(petId);
        favoritoRepository.salvar(favorito);
    }

    public void deletarAnimalDosFavoritos(UUID tutorId, Long petId) {
        Favorito favorito = favoritoRepository.findById(tutorId);

        if (favorito == null || !favorito.getAnimalId().contains(petId)) {
            throw new RuntimeException("Pet não está nos favoritos!");
        }

        favorito.getAnimalId().remove(petId);
        favoritoRepository.salvar(favorito);
    }

    public Set<Long> listarIds(UUID tutorId) {
        Favorito favorito = favoritoRepository.findById(tutorId);
        return favorito == null ? Set.of() : favorito.getAnimalId();
    }
}
