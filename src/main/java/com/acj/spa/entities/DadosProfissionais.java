package com.acj.spa.entities;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DadosProfissionais implements Serializable {

    private String resumo;
    private Integer qtdServicos;

    @DBRef
    private Categoria profissaoPrincipal;

    @DBRef
    private List<Categoria> outrasProfissoes = new ArrayList<>();

    public DadosProfissionais() {
    }

    public DadosProfissionais(String resumo, Integer qtdServicos, Categoria profissaoPrincipal) {
        this.resumo = resumo;
        this.qtdServicos = qtdServicos;
        this.profissaoPrincipal = profissaoPrincipal;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public Integer getQtdServicos() {
        return qtdServicos;
    }

    public void setQtdServicos(Integer qtdServicos) {

        this.qtdServicos = qtdServicos;
    }

    public Categoria getProfissaoPrincipal() {
        return profissaoPrincipal;
    }

    public void setProfissaoPrincipal(Categoria profissaoPrincipal) {
        this.profissaoPrincipal = profissaoPrincipal;
    }

    public List<Categoria> getOutrasProfissoes() {
        return outrasProfissoes;
    }

    public void setOutrasProfissoes(List<Categoria> outrasProfissoes) {
        this.outrasProfissoes = outrasProfissoes;
    }
}
