package com.example.allanimals.application.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FavoritoRequestDto(
        @NotNull(message = "ID do tutor não pode ser nulo")
        UUID tutorid,

        @NotNull
        Long animalId


) {
}
