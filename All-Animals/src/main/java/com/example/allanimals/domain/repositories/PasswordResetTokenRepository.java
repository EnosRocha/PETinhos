package com.example.allanimals.domain.repositories;

import com.example.allanimals.domain.model.entities.PasswordResetToken;

import java.time.LocalDateTime;
import java.util.UUID;

public interface PasswordResetTokenRepository {

    void save(PasswordResetToken token);

    PasswordResetToken findByTokenHash(String tokenHash);

    boolean consume(String tokenHash, LocalDateTime usedAt);

    void invalidateAllByTutorId(UUID tutorId, LocalDateTime usedAt);

    void deleteExpiredBefore(LocalDateTime reference);
}
