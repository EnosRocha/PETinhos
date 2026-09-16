package com.example.allanimals.infrastructure.persistence.jpa.entities;

import com.example.allanimals.domain.model.enums.TipoAnimal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Animal_tb")
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registro;

    @Column(name = "animal_name")
    private String name;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("ordem ASC")
    private List<AnimalImagemEntity> imagens = new ArrayList<>();

    @Column(name = "tipo_de_animal")
    @Enumerated(EnumType.STRING)
    private TipoAnimal tipoAnimal;

    @Column(name = "raca")
    private String raca;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "cor_do_animal")
    private String cor;

    @Column(name = "idade_do_animal")
    private Integer idade;

    @Column(name = "id_do_dono")
    private Integer donoId;

    @Column(name = "descricaao_do_animal")
    private String descricao;

    @Column(name = "endereco_do_animal")
    private String endereco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = true)
    private TutorEntity tutor;


    public Long getRegistro() {

        return registro;
    }

    public void setRegistro(Long registro) {
        this.registro = registro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AnimalImagemEntity> getImagens() {
        return imagens;
    }

    public void setImagens(List<AnimalImagemEntity> imagens) {
        this.imagens = imagens;
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

    public Integer getDonoId() {
        return donoId;
    }

    public void setDonoId(Integer donoId) {
        this.donoId = donoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public TutorEntity getTutor() {
        return tutor;
    }

    public void setTutor(TutorEntity tutor) {
        this.tutor = tutor;
    }
}
