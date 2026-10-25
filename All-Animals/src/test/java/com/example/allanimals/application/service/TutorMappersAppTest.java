package com.example.allanimals.application.service;

import com.example.allanimals.application.dto.TutorRequestDto;
import com.example.allanimals.application.dto.TutorResponseDto;
import com.example.allanimals.application.mappers.TutorMappersApp;
import com.example.allanimals.domain.model.entities.Tutor;
import com.example.allanimals.domain.model.objectValue.Email;
import com.example.allanimals.domain.model.objectValue.Telefone;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TutorMappersAppTest {

    @Test
    void deveMappearDtoParaDomain() {
        TutorRequestDto dto = new TutorRequestDto(
                "Enos", "123456", "84999999999",
                LocalDate.of(2000, 1, 1), "enos@email.com"
        );

        Tutor tutor = TutorMappersApp.toDomain(dto);

        assertEquals("Enos", tutor.getName());
        assertEquals("84999999999", tutor.getPhone());
        assertEquals("enos@email.com", tutor.getEmail().getEmail());
        assertEquals(LocalDate.of(2000, 1, 1), tutor.getBirthday());
    }

    @Test
    void deveRetornarNullQuandoDtoForNull() {
        assertNull(TutorMappersApp.toDomain(null));
    }

    @Test
    void deveMappearDomainParaResponse() {
        Tutor tutor = new Tutor();
        tutor.setTutorId(UUID.randomUUID());
        tutor.setName("Enos");
        tutor.setPhone(new Telefone("84999999999"));
        tutor.setBirthday(LocalDate.of(2000, 1, 1));
        tutor.setEmail(new Email("enos@email.com"));

        TutorResponseDto dto = TutorMappersApp.toResponse(tutor);

        assertEquals("Enos", dto.name());
        assertEquals("enos@email.com", dto.email());
        assertNotNull(dto.phone());
    }

    @Test
    void deveRetornarNullQuandoTutorForNull() {
        assertNull(TutorMappersApp.toResponse(null));
    }

    @Test
    void deveRetornarPhoneNullQuandoTutorSemTelefone() {
        Tutor tutor = new Tutor();
        tutor.setTutorId(UUID.randomUUID());
        tutor.setName("Enos");
        tutor.setEmail(new Email("enos@email.com"));
        tutor.setBirthday(LocalDate.of(2000, 1, 1));

        TutorResponseDto dto = TutorMappersApp.toResponse(tutor);

        assertNull(dto.phone());
    }
}