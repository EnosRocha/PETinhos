package com.example.allanimals.domain.model.entities;

public class AnimalImage {
    private Long id;
    private String url;
    private Integer Ordem;

    public AnimalImage(Long id, String url, Integer ordem) {
        this.id = id;
        this.url = url;
        Ordem = ordem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getOrdem() {
        return Ordem;
    }

    public void setOrdem(Integer ordem) {
        Ordem = ordem;
    }
}
