package com.example.allanimals.interfaces.rest;

import com.example.allanimals.application.service.JwtService;
import com.example.allanimals.application.dto.TutorRequestDto;
import com.example.allanimals.domain.model.entities.Tutor;
import com.example.allanimals.infrastructure.persistence.jpa.impl.TutorRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock private TutorRepositoryImpl tutorRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private JwtService jwtService;
    @Mock private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthController controller;

    private TutorRequestDto criarDto() {
        return new TutorRequestDto(
                "Enos", "123456", "84999999999",
                LocalDate.of(2000, 1, 1), "enos@email.com"
        );
    }

    @Test
    void deveRegistrarERetornarToken() {
        when(passwordEncoder.encode("123456")).thenReturn("hashed");
        when(jwtService.gerarToken("enos@email.com")).thenReturn("token123");

        ResponseEntity<String> response = controller.register(criarDto());

        assertEquals(200, response.getStatusCode().value());
        assertEquals("token123", response.getBody());
        verify(tutorRepository, times(1)).save(any(Tutor.class));
    }

    @Test
    void deveDefinirProviderComoLocal() {
        when(passwordEncoder.encode(any())).thenReturn("hashed");
        when(jwtService.gerarToken(any())).thenReturn("token");

        controller.register(criarDto());

        verify(tutorRepository).save(argThat(t ->
                t.getProvider() == Tutor.AuthProvider.LOCAL
        ));
    }

    @Test
    void deveLogarERetornarToken() {
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(jwtService.gerarToken("enos@email.com")).thenReturn("token123");

        ResponseEntity<String> response = controller.login(criarDto());

        assertEquals(200, response.getStatusCode().value());
        assertEquals("token123", response.getBody());
    }

    @Test
    void deveAutenticarComEmailESenhaCorretosNoLogin() {
        when(jwtService.gerarToken(any())).thenReturn("token");

        controller.login(criarDto());

        verify(authenticationManager).authenticate(
                argThat(auth ->
                        auth instanceof UsernamePasswordAuthenticationToken &&
                                auth.getPrincipal().equals("enos@email.com")
                )
        );
    }
}