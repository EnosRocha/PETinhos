package com.example.allanimals.domain.model.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class PasswordResetToken {

    private UUID id;
    private UUID tutorId;
    private String tokenHash;
    private LocalDateTime expiresAt;
    private LocalDateTime usedAt;
    private LocalDateTime createdAt;
    private String requestedIp;

    public PasswordResetToken() {
    }

    public PasswordResetToken(UUID tutorId, String tokenHash, LocalDateTime expiresAt, LocalDateTime createdAt, String requestedIp) {
        this.tutorId = tutorId;
        this.tokenHash = tokenHash;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.requestedIp = requestedIp;
    }

    public boolean isExpired(LocalDateTime reference) {
        return expiresAt == null || !expiresAt.isAfter(reference);
    }

    public boolean isUsed() {
        return usedAt != null;
    }

    public boolean isUsable(LocalDateTime reference) {
        return !isUsed() && !isExpired(reference);
    }

    public void markAsUsed(LocalDateTime reference) {
        this.usedAt = reference;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTutorId() {
        return tutorId;
    }

    public void setTutorId(UUID tutorId) {
        this.tutorId = tutorId;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public void setTokenHash(String tokenHash) {
        this.tokenHash = tokenHash;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getUsedAt() {
        return usedAt;
    }

    public void setUsedAt(LocalDateTime usedAt) {
        this.usedAt = usedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getRequestedIp() {
        return requestedIp;
    }

    public void setRequestedIp(String requestedIp) {
        this.requestedIp = requestedIp;
    }
}
