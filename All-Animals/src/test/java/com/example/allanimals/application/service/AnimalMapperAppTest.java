package com.example.allanimals.application.service;

import com.example.allanimals.application.dto.AnimalRequestDto;
import com.example.allanimals.application.dto.AnimalResponseDto;
import com.example.allanimals.application.mappers.AnimalMappersApp;
import com.example.allanimals.domain.model.entities.Animal;
import com.example.allanimals.domain.model.enums.TipoAnimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AnimalMapperAppTest {

    @Test
    void deveConverterDtoParaDomain(){
        AnimalRequestDto dto = new AnimalRequestDto(
                "Rex",
                List.of(),
                TipoAnimal.CACHORRO,
                "Labrador",
                25.5,
                "Amarelo",
                5,
                1,
                "Cachorro docil",
                "Rua X"
        );

        Animal animal = AnimalMappersApp.toDomain(dto);

        assertNotNull(animal);
        assertEquals("Rex", animal.getName());
        assertEquals(TipoAnimal.CACHORRO, animal.getTipoAnimal());
        assertEquals("Labrador", animal.getRaca());
        assertEquals(25.5, animal.getPeso());
        assertEquals("Amarelo", animal.getCor());
        assertEquals(5, animal.getIdade());
        assertEquals(1, animal.getDonoId());
        assertEquals("Cachorro docil", animal.getDescricao());
        assertEquals("Rua X", animal.getEndereco());
    }

    @Test
    void deveRetornarNullQuandoRequestDtoForNulo() {

        Animal animal = AnimalMappersApp.toDomain(null);

        assertNull(animal);
    }


    @Test
    void deveConverterAnimalParaResponseDto() {

        Animal animal = new Animal();
        animal.setName("Rex");
        animal.setTipoAnimal(TipoAnimal.CACHORRO);
        animal.setRaca("Labrador");
        animal.setPeso(25.5);
        animal.setCor("Amarelo");
        animal.setIdade(5);
        animal.setDonoId(1);
        animal.setDescricao("Cachorro docil");
        animal.setEndereco("Rua X");

        AnimalResponseDto dto = AnimalMappersApp.toResponse(animal);

        assertNotNull(dto);
        assertEquals("Rex", dto.name());
        assertEquals(TipoAnimal.CACHORRO, dto.tipoAnimal());
        assertEquals("Labrador", dto.raca());
        assertEquals(25.5, dto.peso());
        assertEquals("Amarelo", dto.cor());
        assertEquals(5, dto.idade());
        assertEquals("Cachorro docil", dto.descricao());
        assertEquals("Rua X", dto.endereco());
    }

    @Test
    void deveRetornarNullQuandoAnimalForNulo() {

        AnimalResponseDto dto = AnimalMappersApp.toResponse(null);

        assertNull(dto);
    }







}
