package com.example.allanimals.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ResetPasswordRequestDto(
        @NotBlank(message = "O token é obrigatório")
        String token,

        @NotBlank(message = "A nova senha não pode estar em branco")
        @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres")
        String newPassword,

        @NotBlank(message = "A confirmação da senha é obrigatória")
        String confirmPassword) {
}
