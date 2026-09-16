package com.example.allanimals.application.dto;

import com.example.allanimals.domain.model.entities.AnimalImage;
import com.example.allanimals.domain.model.enums.TipoAnimal;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;

import java.util.List;

public record AnimalRequestDto(
                    @NotBlank(message = "O nome não pode estar em branco")
                    @Size(max = 150, message = "Nome muito longo")
                    String name,

                    @Nullable
                    List<AnimalImage> image,

                    @NotNull(message = "O tipo do animal é obrigatório")
                    TipoAnimal tipoAnimal,

                    @NotBlank(message = "A raça deve ser informada")
                    String raca,

                    @Positive(message = "O peso deve ser maior que zero")
                    Double peso,

                    String cor,

                    @Min(value = 0, message = "Idade não pode ser negativa")
                    Integer idade,

                    @NotNull(message = "O ID do dono é obrigatório")
                    Integer donoId,

                    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
                    String descricao,

                    @NotBlank(message = "O endereço é obrigatório")
                    String endereco) {


}
