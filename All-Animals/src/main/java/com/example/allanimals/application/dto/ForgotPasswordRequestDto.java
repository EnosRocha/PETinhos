package com.example.allanimals.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordRequestDto(
        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Email inválido")
        String email) {
}
