package com.example.allanimals.application.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record TutorRequestDto(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 150, message = "Nome muito longo")
        String name,

        @NotBlank(message = "A senha não pode estar em branco")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String password,

        @NotBlank(message = "O telefone é obrigatório")
        String phone,

        @NotNull(message = "A data de nascimento é obrigatória")
        @Past(message = "A data de nascimento deve ser no passado")
        LocalDate birthday,

        @NotBlank(message = "O email é obrigatório")
        @jakarta.validation.constraints.Email(message = "Email inválido")
        String email) {
}
