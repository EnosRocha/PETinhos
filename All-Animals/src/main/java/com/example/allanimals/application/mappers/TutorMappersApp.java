package com.example.allanimals.application.mappers;

import com.example.allanimals.application.dto.TutorRequestDto;
import com.example.allanimals.application.dto.TutorResponseDto;
import com.example.allanimals.domain.model.entities.Tutor;
import com.example.allanimals.domain.model.objectValue.Email;
import com.example.allanimals.domain.model.objectValue.Telefone;

public class TutorMappersApp {

    public static Tutor toDomain(TutorRequestDto dto) {
        if (dto == null) return null;

        Tutor tutor = new Tutor();

        tutor.setName(dto.name());
        tutor.setPassword(dto.password());
        tutor.setPhone(new Telefone(dto.phone()));
        tutor.setBirthday(dto.birthday());
        tutor.setEmail(new Email(dto.email()));

        return tutor;
    }

    public static TutorResponseDto toResponse(Tutor tutor) {
        if (tutor == null) return null;

        return new TutorResponseDto(
                tutor.getTutorId(),
                tutor.getName(),
                tutor.getPhone() != null ? tutor.getPhone().getFormatado() : null,
                tutor.getBirthday(),
                tutor.getEmail().getEmail()
        );
    }
}
