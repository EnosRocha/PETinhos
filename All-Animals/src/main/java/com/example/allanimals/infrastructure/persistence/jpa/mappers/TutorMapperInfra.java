package com.example.allanimals.infrastructure.persistence.jpa.mappers;

import com.example.allanimals.domain.model.entities.Tutor;
import com.example.allanimals.infrastructure.persistence.jpa.entities.TutorEntity;

public class TutorMapperInfra {


    public static TutorEntity toEntity(Tutor tutor) {
        if (tutor == null) return null;

        TutorEntity entity = new TutorEntity();
        entity.setName(tutor.getName());
        entity.setPassword(tutor.getPassword());
        entity.setPhone(tutor.getPhone());
        entity.setBirthday(tutor.getBirthday());
        entity.setEmail(tutor.getEmail());
        entity.setProvider(TutorEntity.AuthProvider.valueOf(tutor.getProvider().name()));


        return entity;
    }

    public static Tutor toDomain(TutorEntity entity) {
        if (entity == null) return null;

        Tutor tutor = new Tutor();
        tutor.setTutorId(entity.getId());
        tutor.setName(entity.getName());
        tutor.setPassword(entity.getPassword());
        tutor.setPhone(entity.getPhone());
        tutor.setBirthday(entity.getBirthday());
        tutor.setEmail(entity.getEmail());
        tutor.setProvider(Tutor.AuthProvider.valueOf(entity.getProvider().name()));

        return tutor;
    }

}
