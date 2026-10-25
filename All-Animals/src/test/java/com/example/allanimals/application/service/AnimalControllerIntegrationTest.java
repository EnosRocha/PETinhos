package com.example.allanimals.application.service;

import com.example.allanimals.domain.model.enums.TipoAnimal;
import com.example.allanimals.domain.model.objectValue.Email;
import com.example.allanimals.infrastructure.persistence.jpa.entities.AnimalEntity;
import com.example.allanimals.infrastructure.persistence.jpa.entities.TutorEntity;
import com.example.allanimals.infrastructure.persistence.jpa.respositories.AnimalInfraRepository;
import com.example.allanimals.infrastructure.persistence.jpa.respositories.TutorInfraRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class AnimalControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TutorInfraRepository tutorRepository;

    @Autowired
    private AnimalInfraRepository animalRepository;

    private String token;
    private TutorEntity tutor;

    @BeforeEach
    void setup() {
        TutorEntity t = new TutorEntity();
        t.setId(UUID.randomUUID());
        t.setName("Enos");
        t.setEmail(new Email("enos@email.com"));
        t.setPassword("123456");
        t.setProvider(TutorEntity.AuthProvider.LOCAL);
        tutor = tutorRepository.save(t);

        // gera token manualmente
        token = "Bearer " + jwtService.gerarToken("enos@email.com");
    }

    @Autowired
    private JwtService jwtService;

    @Test
    void deveListarAnimais() throws Exception {
        mockMvc.perform(get("/animals")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void deveBuscarAnimalPorId() throws Exception {
        AnimalEntity animal = new AnimalEntity();
        animal.setName("Rex");
        animal.setTipoAnimal(TipoAnimal.CACHORRO);
        animal.setRaca("Labrador");
        animal.setPeso(25.0);
        animal.setCor("Amarelo");
        animal.setIdade(5);
        animal.setDescricao("Dócil");
        animal.setEndereco("Rua X");
        animal.setTutor(tutor);
        AnimalEntity salvo = animalRepository.save(animal);

        mockMvc.perform(get("/animals/" + salvo.getRegistro())
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rex"));
    }

    @Test
    void deveDeletarAnimal() throws Exception {
        AnimalEntity animal = new AnimalEntity();
        animal.setName("Rex");
        animal.setTipoAnimal(TipoAnimal.CACHORRO);
        animal.setRaca("Labrador");
        animal.setPeso(25.0);
        animal.setCor("Amarelo");
        animal.setIdade(5);
        animal.setDescricao("Dócil");
        animal.setEndereco("Rua X");
        animal.setTutor(tutor);
        AnimalEntity salvo = animalRepository.save(animal);

        mockMvc.perform(delete("/animals/" + salvo.getRegistro())
                        .header("Authorization", token))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveBuscarAnimaisDeUmTutor() throws Exception {
        mockMvc.perform(get("/animals/tutor/" + tutor.getId())
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void deveRetornar401SemToken() throws Exception {
        mockMvc.perform(get("/animals"))
                .andExpect(status().isUnauthorized());
    }
}
