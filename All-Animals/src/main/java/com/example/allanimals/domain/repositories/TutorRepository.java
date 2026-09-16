package com.example.allanimals.domain.repositories;

import com.example.allanimals.domain.model.entities.Animal;
import com.example.allanimals.domain.model.entities.Tutor;
import com.example.allanimals.domain.model.objectValue.Email;

import java.lang.ScopedValue;
import java.util.List;
import java.util.UUID;

public interface TutorRepository {

    void save(Tutor tutor);

    void delete(UUID id);

    Tutor update(Tutor tutor);

    List<Tutor> getAllTutors();

    Tutor getTutor(UUID id);

    Tutor findByEmail(Email email);
}
