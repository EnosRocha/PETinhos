package com.example.allanimals.domain.model.entities;

import com.example.allanimals.domain.model.enums.TipoAnimal;
import com.example.allanimals.domain.model.objectValue.RegistroAnimal;

import java.util.UUID;

public class Animal {
    private String name;
    private String image;
    private TipoAnimal tipoAnimal;
    private String raca;
    private Double peso;
    private String cor;
    private Integer idade;
    private RegistroAnimal registro;
    private Integer donoId;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String descricao;
    private String endereco;
    private UUID tutorId;

    public UUID getTutorId() {
        return tutorId;
    }

    public void setTutorId(UUID tutorId) {
        this.tutorId = tutorId;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public RegistroAnimal getRegistro() {
        return registro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setRegistro(RegistroAnimal registro) {
        this.registro = registro;
    }


    public Integer getDonoId() {
        return donoId;
    }

    public void setDonoId(Integer donoId) {
        this.donoId = donoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TipoAnimal getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(TipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}
