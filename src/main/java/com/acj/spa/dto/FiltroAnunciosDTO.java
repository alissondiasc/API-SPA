package com.acj.spa.dto;

import com.acj.spa.enums.StatusAnuncio;
import org.springframework.data.domain.Sort;

public class FiltroAnunciosDTO {
    private String descricao;
    private String cidade;
    private String bairro;
    private StatusAnuncio statusAnuncio;
    private String idProfissional;

    public StatusAnuncio getStatusAnuncio() {
        return statusAnuncio;
    }

    public String getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(String idProfissional) {
        this.idProfissional = idProfissional;
    }

    private int page;
    private int cont = 5;
    private CategoriaDTO categoriaDTO;

    public Sort.Direction getOrderBy() {
        return orderBy;
    }

    private Sort.Direction orderBy = Sort.Direction.DESC;

    public String getDescricao() {
        return descricao;
    }

    public int getPage() {
        return page;
    }

    public int getCont() {
        return cont;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public CategoriaDTO getCategoriaDTO() {
        return categoriaDTO;
    }
}
