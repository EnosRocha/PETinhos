package com.example.allanimals.infrastructure.persistence.jpa.impl;

import com.example.allanimals.domain.model.entities.Tutor;
import com.example.allanimals.domain.model.objectValue.Email;
import com.example.allanimals.domain.repositories.TutorRepository;
import com.example.allanimals.infrastructure.persistence.jpa.entities.TutorEntity;
import com.example.allanimals.infrastructure.persistence.jpa.mappers.TutorMapperInfra;
import com.example.allanimals.infrastructure.persistence.jpa.respositories.TutorInfraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Repository
public class TutorRepositoryImpl implements TutorRepository {


    private final TutorInfraRepository tutorRepository;

    public TutorRepositoryImpl(TutorInfraRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Override
    public void save(Tutor tutor) {
        TutorEntity entity = TutorMapperInfra.toEntity(tutor);
        TutorEntity saved = tutorRepository.save(entity);
    }


    @Override
    public void delete(UUID id) {
        tutorRepository.deleteById(id);
    }

    @Override
    public Tutor update(Tutor tutor) {

        Optional<TutorEntity> tutorEncontrado = tutorRepository.findById(tutor.getTutorId());
        if (!tutorEncontrado.isPresent()) {
            throw new RuntimeException("Tutor não encontrado");
        }
        TutorEntity entity = TutorMapperInfra.toEntity(tutor);
        entity.setId(tutor.getTutorId());
        return TutorMapperInfra.toDomain(tutorRepository.save(entity));
    }

    @Override
    public List<Tutor> getAllTutors() {
        return tutorRepository.findAll()
                .stream()
                .map(TutorMapperInfra::toDomain)
                .toList();
    }


    @Override
    public Tutor getTutor(UUID id) {
        return tutorRepository.findById(id)
                .map(TutorMapperInfra::toDomain)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
    }

    @Override
    public Tutor findByEmail(Email email) {
        TutorEntity tutor = tutorRepository.findByEmail(email);
        Tutor tutorDomain = TutorMapperInfra.toDomain(tutor);
        return tutorDomain;
    }
}
