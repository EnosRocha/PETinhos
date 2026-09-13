package com.example.allanimals.application.service;

import com.example.allanimals.application.dto.AnimalRequestDto;
import com.example.allanimals.domain.model.entities.Animal;
import com.example.allanimals.domain.model.enums.TipoAnimal;
import com.example.allanimals.infrastructure.persistence.jpa.impl.AnimalRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AnimalAppServiceTeste {

    @Mock
    private AnimalRepositoryImpl animalRepository;

    @InjectMocks
    private AnimalAppService animalAppService;


    @Test
    void deveSalvarAnimalCorretamente() {
        AnimalRequestDto dto = new AnimalRequestDto(
                "Rex",
                "https://exemplo.com/rex.jpg",  // image
                TipoAnimal.CACHORRO,
                "Labrador",
                25.5,
                "Amarelo",
                5,
                1,
                "Cachorro dócil",
                "Rua X"
        );

        ArgumentCaptor<Animal> captor = ArgumentCaptor.forClass(Animal.class);

        animalAppService.cadastrarAnimal(dto);

        verify(animalRepository).save(captor.capture());

        Animal animalSalvo = captor.getValue();

        assertEquals("Rex", animalSalvo.getName());
        assertEquals("https://exemplo.com/rex.jpg", animalSalvo.getImage());
        assertEquals(TipoAnimal.CACHORRO, animalSalvo.getTipoAnimal());
        assertEquals("Labrador", animalSalvo.getRaca());
        assertEquals(25.5, animalSalvo.getPeso());
        assertEquals("Amarelo", animalSalvo.getCor());
        assertEquals(5, animalSalvo.getIdade());
        assertEquals(1, animalSalvo.getDonoId());
        assertEquals("Cachorro dócil", animalSalvo.getDescricao());
        assertEquals("Rua X", animalSalvo.getEndereco());
    }

    @Test
    public void deveLancarIllegalExceptionQuandoDtoForNulo() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> animalAppService.cadastrarAnimal(null)
        );
        assertEquals("Animals information cant be null", exception.getMessage());
        verify(animalRepository, times(0)).save(any());
    }

    @Test
    public void deletarAnimal() {
        Long id = 1L;
        animalAppService.deletarAnimal(id);
        verify(animalRepository, times(1)).delete(any(Long.class));
    }

    @Test
    void deveLancarExcecaoQuandoRegistroForNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> animalAppService.deletarAnimal(null)
        );
        assertEquals("Animals information cant be null", exception.getMessage());
        verify(animalRepository, times(0)).delete(any());
    }
}