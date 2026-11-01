package com.example.allanimals.application.service;

import com.example.allanimals.domain.exceptions.FavoritoException;
import com.example.allanimals.domain.repositories.FavoritoRepository;
import com.example.allanimals.domain.service.FavoritoService;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class FavoritoAppService {

    private final FavoritoService favoritoService;
    private final FavoritoRepository favoritoRepository;

    public FavoritoAppService(FavoritoService favoritoService, FavoritoRepository favoritoRepository) {
        this.favoritoService = favoritoService;
        this.favoritoRepository = favoritoRepository;
    }

    @Transactional
    public void favoritar(UUID tutorId, Long petId) {
        boolean exists = favoritoRepository.existe(tutorId, petId);

        if (exists) throw new FavoritoException("Nao ha lista de favoritos para esse tutor");

        favoritoService.atribuirAnimalFavoritoAoTutor(tutorId, petId);


    }

    @Transactional
    public void desfavoritar(UUID tutorId, Long petId) {
        favoritoService.deletarAnimalDosFavoritos(tutorId, petId);
    }

    @Transactional
    @Cacheable(value = "favoritos", key = "#tutorId")
    public Set<Long> listar(UUID tutorId) {
        return favoritoService.listarIds(tutorId);
    }
}
