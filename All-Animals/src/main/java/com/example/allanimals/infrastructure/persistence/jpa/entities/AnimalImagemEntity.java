package com.example.allanimals.infrastructure.persistence.jpa.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "animal_imagens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalImagemEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private AnimalEntity animal;

    @Column(name = "url")
    private String url;

    @Column(name = "ordem")
    private Integer ordem;

}
