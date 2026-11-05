package com.example.allanimals.infrastructure.persistence.jpa.impl;

import com.example.allanimals.domain.model.entities.PasswordResetToken;
import com.example.allanimals.domain.repositories.PasswordResetTokenRepository;
import com.example.allanimals.infrastructure.persistence.jpa.mappers.PasswordResetTokenMapperInfra;
import com.example.allanimals.infrastructure.persistence.jpa.respositories.PasswordResetTokenInfraRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public class PasswordResetTokenRepositoryImpl implements PasswordResetTokenRepository {

    private final PasswordResetTokenInfraRepository repository;

    public PasswordResetTokenRepositoryImpl(PasswordResetTokenInfraRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(PasswordResetToken token) {
        repository.save(PasswordResetTokenMapperInfra.toEntity(token));
    }

    @Override
    public PasswordResetToken findByTokenHash(String tokenHash) {
        return repository.findByTokenHash(tokenHash)
                .map(PasswordResetTokenMapperInfra::toDomain)
                .orElse(null);
    }

    @Override
    public boolean consume(String tokenHash, LocalDateTime usedAt) {
        return repository.consume(tokenHash, usedAt) > 0;
    }

    @Override
    public void invalidateAllByTutorId(UUID tutorId, LocalDateTime usedAt) {
        repository.invalidateAllByTutorId(tutorId, usedAt);
    }

    @Override
    public void deleteExpiredBefore(LocalDateTime reference) {
        repository.deleteByExpiresAtBefore(reference);
    }
}
