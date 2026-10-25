package com.example.allanimals.application.service;

import com.example.allanimals.application.dto.TutorRequestDto;
import com.example.allanimals.application.dto.TutorResponseDto;
import com.example.allanimals.interfaces.rest.TutorController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TutorControllerTest {

    @Mock
    private TutorAppService service;

    @InjectMocks
    private TutorController controller;

    private TutorRequestDto criarDto() {
        return new TutorRequestDto(
                "Enos", "123456", "84999999999",
                LocalDate.of(2000, 1, 1), "enos@email.com"
        );
    }

    private TutorResponseDto criarResponse() {
        return new TutorResponseDto(
                UUID.randomUUID(), "Enos", "84999999999",
                LocalDate.of(2000, 1, 1), "enos@email.com"
        );
    }

    @Test
    void deveCadastrarTutorERetornar201() {
        doNothing().when(service).cadastrarTutor(any());
        ResponseEntity<Void> response = controller.cadastrar(criarDto());
        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    void deveDeletarTutorERetornar204() {
        UUID id = UUID.randomUUID();
        doNothing().when(service).deleteTutor(id);
        ResponseEntity<Void> response = controller.deletar(id);
        assertEquals(204, response.getStatusCode().value());
    }

    @Test
    void deveAtualizarTutorERetornar204() {
        UUID id = UUID.randomUUID();
        doNothing().when(service).updateTutor(eq(id), any());
        ResponseEntity<TutorResponseDto> response = controller.atualizar(id, criarDto());
        assertEquals(204, response.getStatusCode().value());
    }

    @Test
    void deveListarTutores() {
        when(service.getAllTutors()).thenReturn(List.of(criarResponse()));
        ResponseEntity<List<TutorResponseDto>> response = controller.listar();
        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void deveBuscarTutorPorId() {
        UUID id = UUID.randomUUID();
        when(service.getTutor(id)).thenReturn(criarResponse());
        ResponseEntity<TutorResponseDto> response = controller.buscarPorId(id);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Enos", response.getBody().name());
    }

    @Test
    void deveBuscarMeuPerfil() {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("enos@email.com");
        when(service.getTutorByEmail("enos@email.com")).thenReturn(criarResponse());

        ResponseEntity<TutorResponseDto> response = controller.buscarMeuPerfil(auth);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("enos@email.com", response.getBody().email());
    }
}