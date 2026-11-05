package com.example.allanimals.infrastructure.persistence.jpa.respositories;

import com.example.allanimals.infrastructure.persistence.jpa.entities.PasswordResetTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface PasswordResetTokenInfraRepository extends JpaRepository<PasswordResetTokenEntity, UUID> {

    Optional<PasswordResetTokenEntity> findByTokenHash(String tokenHash);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update PasswordResetTokenEntity token set token.usedAt = :usedAt where token.tokenHash = :tokenHash and token.usedAt is null")
    int consume(@Param("tokenHash") String tokenHash, @Param("usedAt") LocalDateTime usedAt);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update PasswordResetTokenEntity token set token.usedAt = :usedAt where token.tutorId = :tutorId and token.usedAt is null")
    int invalidateAllByTutorId(@Param("tutorId") UUID tutorId, @Param("usedAt") LocalDateTime usedAt);

    long deleteByExpiresAtBefore(LocalDateTime reference);
}
