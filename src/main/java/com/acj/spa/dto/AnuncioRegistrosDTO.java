package com.acj.spa.dto;

import com.acj.spa.enums.StatusAnuncio;

import java.time.LocalDateTime;
import java.util.List;

public class AnuncioRegistrosDTO {
    private String id;
    private String titulo;
    private String descricao;
    private CategoriaAnuncioDTO categoria;
    private LocalDateTime dataHora;
    private List<UsuarioSimplificadoDTO> candidatos;
    private UsuarioSimplificadoDTO anunciante;
    private StatusAnuncio status;
    private UsuarioSimplificadoDTO profissional;
    private UsuarioSimplificadoDTO usuario;
    private String localidade;
    private String bairro;

    public UsuarioSimplificadoDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSimplificadoDTO usuario) {
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CategoriaAnuncioDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaAnuncioDTO categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public List<UsuarioSimplificadoDTO> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<UsuarioSimplificadoDTO> candidatos) {
        this.candidatos = candidatos;
    }

    public UsuarioSimplificadoDTO getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(UsuarioSimplificadoDTO anunciante) {
        this.anunciante = anunciante;
    }

    public StatusAnuncio getStatus() {
        return status;
    }

    public void setStatus(StatusAnuncio status) {
        this.status = status;
    }

    public UsuarioSimplificadoDTO getProfissional() {
        return profissional;
    }

    public void setProfissional(UsuarioSimplificadoDTO profissional) {
        this.profissional = profissional;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
