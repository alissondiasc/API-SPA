package com.acj.spa.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Categoria implements Serializable {

    @Id
    private String id;
    private String nome;

    public Categoria() {
    }

    public Categoria(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public Categoria(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
