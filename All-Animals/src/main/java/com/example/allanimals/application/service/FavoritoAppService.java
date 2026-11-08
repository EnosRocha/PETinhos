package com.example.allanimals.application.service;

import com.example.allanimals.application.dto.FavoritoResponseDto;
import com.example.allanimals.domain.exceptions.FavoritoException;
import com.example.allanimals.domain.model.entities.Animal;
import com.example.allanimals.domain.repositories.AnimalRepository;
import com.example.allanimals.domain.repositories.FavoritoRepository;
import com.example.allanimals.domain.service.FavoritoService;
import com.example.allanimals.infrastructure.persistence.jpa.entities.AnimalEntity;
import com.example.allanimals.infrastructure.persistence.jpa.mappers.AnimalMapperInfra;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FavoritoAppService {

    private final FavoritoService favoritoService;
    private final FavoritoRepository favoritoRepository;
    private final AnimalRepository animalRepository;

    public FavoritoAppService(FavoritoService favoritoService, FavoritoRepository favoritoRepository, AnimalRepository animalRepository) {
        this.favoritoService = favoritoService;
        this.favoritoRepository = favoritoRepository;
        this.animalRepository = animalRepository;
    }

    @Transactional
    @CacheEvict(value = "favoritos", key = "#tutorId")
    public void favoritar(UUID tutorId, Long petId) {
        boolean exists = favoritoRepository.existe(tutorId, petId);

        if (exists) throw new FavoritoException("Este pet já está nos favoritos");

        favoritoService.atribuirAnimalFavoritoAoTutor(tutorId, petId);


    }

    @Transactional
    @CacheEvict(value = "favoritos", key = "#tutorId")
    public void desfavoritar(UUID tutorId, Long petId) {
        favoritoService.deletarAnimalDosFavoritos(tutorId, petId);
    }

    @Transactional
    @Cacheable(value = "favoritos", key = "#tutorId")
    public Set<Long> listar(UUID tutorId) {
        return favoritoService.listarIds(tutorId);
    }

    @Transactional
    @Cacheable(value = "favoritos-animais", key = "#tutorId")
    public List<Animal> listarAnimais(UUID tutorId) {
        List<Long> ids = favoritoRepository.listarAnimalIdsPorUsuario(tutorId);
        return ids.stream()
                .map(id -> animalRepository.getAnimal(id))
                .collect(Collectors.toList());
    }
}
