package com.example.allanimals.application.service;

import com.example.allanimals.domain.model.entities.Tutor;
import com.example.allanimals.domain.model.objectValue.Email;
import com.example.allanimals.infrastructure.persistence.jpa.impl.TutorRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @Mock private TutorRepositoryImpl tutorRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    private Tutor criarTutor() {
        Tutor t = new Tutor();
        t.setTutorId(UUID.randomUUID());
        t.setEmail(new Email("enos@email.com"));
        t.setPassword("hashed_password");
        return t;
    }

    @Test
    void deveCarregarUserDetailsPorEmail() {
        when(tutorRepository.findByEmail(any(Email.class))).thenReturn(criarTutor());

        UserDetails details = userDetailsService.loadUserByUsername("enos@email.com");

        assertEquals("enos@email.com", details.getUsername());
        assertEquals("hashed_password", details.getPassword());
    }

    @Test
    void deveDefinirPasswordVazioQuandoPasswordForNull() {
        Tutor tutor = criarTutor();
        tutor.setPassword(null);
        when(tutorRepository.findByEmail(any(Email.class))).thenReturn(tutor);

        UserDetails details = userDetailsService.loadUserByUsername("enos@email.com");

        assertEquals("", details.getPassword());
    }

    @Test
    void deveTerRoleUser() {
        when(tutorRepository.findByEmail(any(Email.class))).thenReturn(criarTutor());

        UserDetails details = userDetailsService.loadUserByUsername("enos@email.com");

        assertTrue(details.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
    }
}