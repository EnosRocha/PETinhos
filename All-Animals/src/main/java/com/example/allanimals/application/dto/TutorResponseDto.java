package com.example.allanimals.application.dto;

import java.time.LocalDate;
import java.util.UUID;

public record TutorResponseDto(

        UUID id,

        String name,

        String phone,

        LocalDate birthday,

        String email
) {
}
