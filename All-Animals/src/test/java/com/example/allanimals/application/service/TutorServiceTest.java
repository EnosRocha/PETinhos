package com.example.allanimals.application.service;

import com.example.allanimals.domain.exceptions.TutorException;
import com.example.allanimals.domain.model.entities.Tutor;
import com.example.allanimals.domain.model.objectValue.Email;
import com.example.allanimals.domain.model.objectValue.Telefone;
import com.example.allanimals.domain.repositories.TutorRepository;
import com.example.allanimals.domain.service.TutorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TutorServiceTest {

    @Mock
    private TutorRepository tutorRepository;

    @InjectMocks
    private TutorService tutorService;

    private Tutor criarTutor() {
        Tutor t = new Tutor();
        t.setTutorId(UUID.randomUUID());
        t.setName("Enos");
        t.setEmail(new Email("enos@email.com"));
        t.setPhone(new Telefone("84999999999"));
        t.setBirthday(LocalDate.of(2000, 1, 1));
        return t;
    }

    @Test
    void deveCadastrarTutor() {
        Tutor tutor = criarTutor();
        tutorService.cadastrarTutor(tutor);
        verify(tutorRepository, times(1)).save(tutor);
    }

    @Test
    void deveBuscarTutorPorId() {
        UUID id = UUID.randomUUID();
        Tutor tutor = criarTutor();
        when(tutorRepository.getTutor(id)).thenReturn(tutor);

        Tutor resultado = tutorService.getTutor(id);

        assertEquals("Enos", resultado.getName());
    }

    @Test
    void deveListarTodos() {
        when(tutorRepository.getAllTutors()).thenReturn(List.of(criarTutor()));

        List<Tutor> result = tutorService.getAllTutors();

        assertEquals(1, result.size());
    }

    @Test
    void deveLancarExcecaoQuandoListaForNula() {
        when(tutorRepository.getAllTutors()).thenReturn(null);
        assertThrows(TutorException.class, () -> tutorService.getAllTutors());
    }

    @Test
    void deveAtualizarTutor() {
        Tutor tutor = criarTutor();
        tutorService.updateTutor(tutor);
        verify(tutorRepository, times(1)).update(tutor);
    }

    @Test
    void deveDeletarTutor() {
        UUID id = UUID.randomUUID();
        tutorService.deleteTutor(id);
        verify(tutorRepository, times(1)).delete(id);
    }
}