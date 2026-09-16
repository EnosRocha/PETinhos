package com.example.allanimals.infrastructure.persistence.jpa.respositories;

import com.example.allanimals.domain.model.objectValue.Email;
import com.example.allanimals.infrastructure.persistence.jpa.entities.TutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TutorInfraRepository extends JpaRepository<TutorEntity, UUID> {

    TutorEntity findByEmail(Email email);
}
