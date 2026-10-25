package com.example.allanimals.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

public class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setup() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secret",
                "PETinhos2026SuperSecretKeyParaJWTComMaisDe256BitsDeSeguranca123456789");
        ReflectionTestUtils.setField(jwtService, "expiration", 86400000L);
    }

    @Test
    void deveGerarToken() {
        String token = jwtService.gerarToken("enos@email.com");
        assertNotNull(token);
        assertFalse(token.isBlank());
    }

    @Test
    void deveExtrairEmailDoToken() {
        String token = jwtService.gerarToken("enos@email.com");
        String email = jwtService.extrairEmail(token);
        assertEquals("enos@email.com", email);
    }

    @Test
    void deveValidarTokenValido() {
        String token = jwtService.gerarToken("enos@email.com");
        assertTrue(jwtService.tokenValido(token));
    }

    @Test
    void deveRetornarFalseParaTokenInvalido() {
        assertFalse(jwtService.tokenValido("token.invalido.aqui"));
    }

    @Test
    void deveRetornarFalseParaTokenVazio() {
        assertFalse(jwtService.tokenValido(""));
    }
}