package com.example.allanimals.domain.service;

import com.example.allanimals.domain.exceptions.PasswordResetException;
import com.example.allanimals.domain.model.entities.PasswordResetToken;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HexFormat;

@Service
public class PasswordResetService {

    private static final int TOKEN_BYTES = 32;

    private final SecureRandom secureRandom = new SecureRandom();

    public String generateToken() {
        byte[] bytes = new byte[TOKEN_BYTES];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public String hash(String rawToken) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashed = digest.digest(rawToken.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hashed);
        } catch (NoSuchAlgorithmException exception) {
            throw new PasswordResetException("Não foi possível processar o token");
        }
    }

    public void ensureUsable(PasswordResetToken token, LocalDateTime now) {
        if (token == null || !token.isUsable(now)) {
            throw new PasswordResetException("Token inválido ou expirado");
        }
    }
}
