package com.acj.spa.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DadosProfissionaisDTO implements Serializable{

	private String resumo;
	private Integer qtdServicos;
	private CategoriaDTO profissaoPrincipal;
	private List<CategoriaDTO> outrasProfissoes = new ArrayList<>();

    public DadosProfissionaisDTO(String resumo, Integer qtdServicos, CategoriaDTO profissaoPrincipal) {
        this.resumo = resumo;
        this.qtdServicos = qtdServicos;
        this.profissaoPrincipal = profissaoPrincipal;
    }

    public DadosProfissionaisDTO() {
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

    public CategoriaDTO getProfissaoPrincipal() {
        return profissaoPrincipal;
    }

    public void setProfissaoPrincipal(CategoriaDTO profissaoPrincipal) {
        this.profissaoPrincipal = profissaoPrincipal;
    }

    public List<CategoriaDTO> getOutrasProfissoes() {
        return outrasProfissoes;
    }

    public void setOutrasProfissoes(List<CategoriaDTO> outrasProfissoes) {
        this.outrasProfissoes = outrasProfissoes;
    }
}
