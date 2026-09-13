package com.example.allanimals.application.dto;

import com.example.allanimals.domain.model.enums.TipoAnimal;

public record AnimalResponseDto(
        Long registro,
        String name,
        String image,
        TipoAnimal tipoAnimal,
        String raca,
        Double peso,
        String cor,
        Integer idade,
        Integer donoId,
        String descricao,
        String endereco
) {


}
