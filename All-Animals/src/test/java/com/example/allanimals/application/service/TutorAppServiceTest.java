package com.example.allanimals.application.service;

import com.example.allanimals.application.dto.TutorRequestDto;
import com.example.allanimals.application.dto.TutorResponseDto;
import com.example.allanimals.domain.exceptions.TutorException;
import com.example.allanimals.domain.model.entities.Tutor;
import com.example.allanimals.domain.model.objectValue.Email;
import com.example.allanimals.domain.model.objectValue.Telefone;
import com.example.allanimals.infrastructure.persistence.jpa.impl.TutorRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TutorAppServiceTest {

    @Mock
    private TutorRepositoryImpl tutorRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private TutorAppService service;

    private TutorRequestDto criarDto() {
        return new TutorRequestDto(
                "Enos", "123456", "84999999999",
                LocalDate.of(2000, 1, 1), "enos@email.com"
        );
    }

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
        when(passwordEncoder.encode("123456")).thenReturn("hashed");
        service.cadastrarTutor(criarDto());
        verify(tutorRepository, times(1)).save(any(Tutor.class));
    }

    @Test
    void deveBuscarTutorPorId() {
        UUID id = UUID.randomUUID();
        Tutor tutor = criarTutor();
        tutor.setTutorId(id);
        when(tutorRepository.getTutor(id)).thenReturn(tutor);

        TutorResponseDto dto = service.getTutor(id);

        assertEquals("Enos", dto.name());
        verify(tutorRepository, times(1)).getTutor(id);
    }

    @Test
    void deveListarTodosOsTutores() {
        when(tutorRepository.getAllTutors()).thenReturn(List.of(criarTutor()));

        List<TutorResponseDto> result = service.getAllTutors();

        assertEquals(1, result.size());
    }

    @Test
    void deveLancarExcecaoQuandoListaForNula() {
        when(tutorRepository.getAllTutors()).thenReturn(null);

        assertThrows(TutorException.class, () -> service.getAllTutors());
    }

    @Test
    void deveAtualizarTutor() {
        UUID id = UUID.randomUUID();
        Tutor tutor = criarTutor();
        tutor.setTutorId(id);
        when(tutorRepository.getTutor(id)).thenReturn(tutor);

        service.updateTutor(id, criarDto());

        verify(tutorRepository, times(1)).update(tutor);
    }

    @Test
    void deveDeletarTutor() {
        UUID id = UUID.randomUUID();
        service.deleteTutor(id);
        verify(tutorRepository, times(1)).delete(id);
    }

    @Test
    void deveBuscarTutorPorEmail() {
        Tutor tutor = criarTutor();
        when(tutorRepository.findByEmail(any(Email.class))).thenReturn(tutor);

        TutorResponseDto dto = service.getTutorByEmail("enos@email.com");

        assertEquals("Enos", dto.name());
    }
}