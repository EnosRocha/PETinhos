package com.example.allanimals.infrastructure.persistence.jpa.respositories;

import com.example.allanimals.infrastructure.persistence.jpa.entities.AnimalImagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalImageRepository extends JpaRepository<AnimalImagemEntity, Long> {

    List<AnimalImagemEntity> findByAnimalRegistroOrderByOrdemAsc(Long registro);

}
