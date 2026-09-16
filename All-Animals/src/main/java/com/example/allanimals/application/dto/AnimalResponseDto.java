package com.example.allanimals.application.dto;

import com.example.allanimals.domain.model.entities.AnimalImage;
import com.example.allanimals.domain.model.enums.TipoAnimal;

import java.util.List;

public record AnimalResponseDto(
        Long registro,
        String name,
        List<AnimalImage> image,
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
