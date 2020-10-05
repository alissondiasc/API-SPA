package com.acj.spa.entities;

import com.acj.spa.dto.UsuarioDTO;
import com.acj.spa.enums.StatusAnuncio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Document
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anuncio implements Serializable {

    @Id
    private String id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private StatusAnuncio status = StatusAnuncio.NOVO;
    private String localidade ;
    private String bairro;
    private Boolean isDeleted = false;

    @DBRef
    private Categoria categoria;

    @DBRef
    private Usuario usuario;

    @DBRef
    private Usuario profissional;

    @DBRef
    private List<Usuario> candidatos;
    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Anuncio(String id, String titulo, String descricao, Categoria categoria, Usuario usuario, LocalDateTime dataHora, List<Usuario> candidatos, Usuario profissional,String localidade,String bairro ) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.bairro = bairro;
        this.categoria = categoria;
        this.usuario = usuario;
        this.profissional = profissional;
        this.candidatos = candidatos;
        this.localidade = localidade;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario) {
            Usuario qualquer = (Usuario) obj;
            return this.id.equals(qualquer.getId());
        }else {
            return false;
        }
    }
}
