package com.acj.spa.dto;

public class UsuarioSimplificadoDTO {
    private String id;
    private String nome;
    private String fotoUsuario;
    private DadosProfissionaisDTO dadosProfissionais;

    public DadosProfissionaisDTO getDadosProfissionais() {
        return dadosProfissionais;
    }

    public void setDadosProfissionais(DadosProfissionaisDTO dadosProfissionais) {
        this.dadosProfissionais = dadosProfissionais;
    }

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
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
