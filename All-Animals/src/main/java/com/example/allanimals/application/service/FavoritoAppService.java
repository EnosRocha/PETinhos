package com.example.allanimals.application.service;

import com.example.allanimals.domain.service.FavoritoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class FavoritoAppService {

    private final FavoritoService favoritoService;

    public FavoritoAppService(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @Transactional
    public void favoritar(UUID tutorId, Long petId) {
        favoritoService.atribuirAnimalFavoritoAoTutor(tutorId, petId);
    }

    @Transactional
    public void desfavoritar(UUID tutorId, Long petId) {
        favoritoService.deletarAnimalDosFavoritos(tutorId, petId);
    }

    @Transactional
    public Set<Long> listar(UUID tutorId) {
        return favoritoService.listarIds(tutorId);
    }
}
