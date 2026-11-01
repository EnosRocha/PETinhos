package com.example.allanimals.application.dto;

import java.util.UUID;

public record FavoritoResponseDto(
        Long id,
        UUID tutorid,
        Long animalId

) {
}
