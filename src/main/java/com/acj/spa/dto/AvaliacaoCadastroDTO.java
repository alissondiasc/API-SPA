package com.acj.spa.dto;

import com.acj.spa.entities.Anuncio;
import com.acj.spa.entities.Usuario;

import java.io.Serializable;

public class AvaliacaoCadastroDTO implements Serializable {

    private String id;
    private Integer nota;
    private String comentario;
    private Usuario usuario;
    private Anuncio anuncio;

    public AvaliacaoCadastroDTO(String ig, Integer nota, String comentario, Usuario usuario) {
        this.id =id;
        this.nota = nota;
        this.comentario = comentario;
        this.usuario = usuario;
    }

    public AvaliacaoCadastroDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
