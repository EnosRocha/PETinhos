package com.example.allanimals.application.service;

import com.example.allanimals.domain.model.entities.Animal;
import com.example.allanimals.domain.model.enums.TipoAnimal;
import com.example.allanimals.domain.repositories.AnimalRepository;
import com.example.allanimals.domain.service.AnimalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    @Test
    private Animal criarAnimal() {
        Animal animal = new Animal();
        animal.setName("Rex");
        animal.setTipoAnimal(TipoAnimal.CACHORRO);
        animal.setRaca("Labrador");
        animal.setPeso(25.0);
        animal.setCor("Amarelo");
        animal.setIdade(5);
        animal.setDescricao("Dócil");
        animal.setEndereco("Rua X");
        return animal;
    }

    @Test
    void deveCadastrarAnimal() {
        Animal animal = criarAnimal();

        animalService.cadastrarAnimal(animal);

        verify(animalRepository, times(1)).save(animal);
    }

    @Test
    void deveChamarSaveComOAnimalCorreto() {
        Animal animal = criarAnimal();

        animalService.cadastrarAnimal(animal);

        verify(animalRepository).save(animal);
        verifyNoMoreInteractions(animalRepository);
    }

    @Test
    void deveRetornarListaDeAnimais() {
        List<Animal> lista = List.of(criarAnimal(), criarAnimal());
        when(animalRepository.getAllAnimals()).thenReturn(lista);

        List<Animal> resultado = animalService.getAllAnimais();

        assertEquals(2, resultado.size());
        verify(animalRepository, times(1)).getAllAnimals();
    }

    @Test
    void deveRetornarListaVazia() {
        when(animalRepository.getAllAnimals()).thenReturn(List.of());

        List<Animal> resultado = animalService.getAllAnimais();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveAtualizarAnimal() {
        Animal animal = criarAnimal();

        animalService.updateAnimal(animal);

        verify(animalRepository, times(1)).update(animal);
    }

    @Test
    void deveDeletarAnimal() {
        Long id = 1L;

        animalService.deleteAnimal(id);

        verify(animalRepository, times(1)).delete(id);
    }

    @Test
    void deveChamarDeleteComIdCorreto() {
        Long id = 42L;

        animalService.deleteAnimal(id);

        verify(animalRepository).delete(42L);
    }
}
