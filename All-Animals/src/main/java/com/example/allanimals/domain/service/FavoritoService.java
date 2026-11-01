package com.example.allanimals.domain.service;

import com.example.allanimals.domain.model.entities.Favorito;
import com.example.allanimals.domain.repositories.AnimalRepository;
import com.example.allanimals.domain.repositories.FavoritoRepository;
import com.example.allanimals.domain.repositories.TutorRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
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
        Favorito favorito = new Favorito();
        favorito.setUsuarioId(tutorId);
        favorito.setAnimalId(new HashSet<>(Set.of(petId)));
        favoritoRepository.salvar(favorito);
    }

    public void deletarAnimalDosFavoritos(UUID tutorId, Long petId) {
        favoritoRepository.remover(tutorId, petId);
    }

    public Set<Long> listarIds(UUID tutorId) {
        Favorito favorito = favoritoRepository.findById(tutorId);
        return favorito == null ? Set.of() : favorito.getAnimalId();
    }

}
