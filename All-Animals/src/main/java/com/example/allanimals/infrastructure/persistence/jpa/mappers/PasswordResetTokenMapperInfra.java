package com.example.allanimals.infrastructure.persistence.jpa.mappers;

import com.example.allanimals.domain.model.entities.PasswordResetToken;
import com.example.allanimals.infrastructure.persistence.jpa.entities.PasswordResetTokenEntity;

public class PasswordResetTokenMapperInfra {

    public static PasswordResetTokenEntity toEntity(PasswordResetToken token) {
        if (token == null) {
            return null;
        }

        PasswordResetTokenEntity entity = new PasswordResetTokenEntity();
        entity.setId(token.getId());
        entity.setTutorId(token.getTutorId());
        entity.setTokenHash(token.getTokenHash());
        entity.setExpiresAt(token.getExpiresAt());
        entity.setUsedAt(token.getUsedAt());
        entity.setCreatedAt(token.getCreatedAt());
        entity.setRequestedIp(token.getRequestedIp());

        return entity;
    }

    public static PasswordResetToken toDomain(PasswordResetTokenEntity entity) {
        if (entity == null) {
            return null;
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setId(entity.getId());
        token.setTutorId(entity.getTutorId());
        token.setTokenHash(entity.getTokenHash());
        token.setExpiresAt(entity.getExpiresAt());
        token.setUsedAt(entity.getUsedAt());
        token.setCreatedAt(entity.getCreatedAt());
        token.setRequestedIp(entity.getRequestedIp());

        return token;
    }
}
