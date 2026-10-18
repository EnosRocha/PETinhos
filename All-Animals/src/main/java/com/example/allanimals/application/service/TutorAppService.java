package com.example.allanimals.application.service;


import com.example.allanimals.application.dto.TutorRequestDto;
import com.example.allanimals.application.dto.TutorResponseDto;
import com.example.allanimals.application.mappers.TutorMappersApp;
import com.example.allanimals.domain.exceptions.TutorException;
import com.example.allanimals.domain.model.entities.Tutor;
import com.example.allanimals.domain.model.objectValue.Email;
import com.example.allanimals.domain.model.objectValue.Telefone;
import com.example.allanimals.infrastructure.persistence.jpa.impl.TutorRepositoryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TutorAppService {

    private final TutorRepositoryImpl tutorRepository;


    public TutorAppService(TutorRepositoryImpl tutorRepository, PasswordEncoder passwordEncoder) {
        this.tutorRepository = tutorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void cadastrarTutor(TutorRequestDto tutorRequestDto) {
        Tutor tutor = TutorMappersApp.toDomain(tutorRequestDto);
        tutor.setPassword(passwordEncoder.encode(tutorRequestDto.password()));
        tutorRepository.save(tutor);

    }


    public TutorResponseDto getTutor(UUID id) {
        Tutor tutor = tutorRepository.getTutor(id);
        TutorResponseDto tutorResponseDto = TutorMappersApp.toResponse(tutor);
        return tutorResponseDto;
    }


    public List<TutorResponseDto> getAllTutors() {
        List<Tutor> result = tutorRepository.getAllTutors();

        if (result == null || result.equals("") || result.equals("undefined")) {
            throw new TutorException("Nenhum tutor encontrado");
        }

        return result.stream().map(TutorMappersApp::toResponse).collect(Collectors.toList());


    }

    public void updateTutor(UUID id, TutorRequestDto tutorRequestDto) {
        Tutor tutorEncontrado = tutorRepository.getTutor(id);

        if (tutorRequestDto.birthday() != null && !tutorRequestDto.birthday().equals("")) {
            tutorEncontrado.setBirthday(tutorRequestDto.birthday());
        }
        if (tutorRequestDto.name() != null && !tutorRequestDto.name().equals("")) {
            tutorEncontrado.setName(tutorRequestDto.name());
        }
        if (tutorRequestDto.email() != null && !tutorRequestDto.email().equals("")) {
            tutorEncontrado.setEmail(new Email(tutorRequestDto.email()));
        }
        if (tutorRequestDto.phone() != null && !tutorRequestDto.phone().equals("")) {
            tutorEncontrado.setPhone(new Telefone(tutorRequestDto.phone()));
        }

        tutorRepository.update(tutorEncontrado);
    }


    public void deleteTutor(UUID id) {
        tutorRepository.delete(id);
    }

    public TutorResponseDto getTutorByEmail(String email) {
        Tutor tutor = tutorRepository.findByEmail(new Email(email));
        return TutorMappersApp.toResponse(tutor);
    }
}
