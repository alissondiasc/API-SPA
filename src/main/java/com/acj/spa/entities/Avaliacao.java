package com.acj.spa.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


public class Avaliacao implements Serializable {


    private Integer nota;
    private String comentario;


    @DBRef
    private Usuario usuario;
    @DBRef
    private Anuncio anuncio;

    public Avaliacao() {
    }

    public Avaliacao(String id, Integer nota, String comentario, Usuario usuario, Anuncio anuncio) {

        this.nota = nota;
        this.comentario = comentario;
        this.usuario = usuario;
        this.anuncio = anuncio;
    }


    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }
}
