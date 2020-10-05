package com.acj.spa.entity;

import com.acj.spa.enums.Escolaridade;
import com.acj.spa.enums.Sexo;
import com.acj.spa.enums.TipoPerfil;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario  extends GenericEntity implements Serializable  {
    private String nome;
    @Indexed(unique = true)
    private String email;
    @DBRef
    private List<Perfil> perfils;
    private String senha;
    private Perfil perfil;
    private Endereco endereco;
    private DadosProfissionais dadosProfissionais;
    private String cpf;
    private String rg;
    private String orgaoExpedidor;
    private Sexo sexo;
    private Escolaridade escolaridade;
    private String nacionalidade;
    private String telefone;
    private String celular;
    private String fotoUsuario;

	@JsonBackReference
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public Usuario(Usuario usuario) {
        super(usuario.id);
    }

    public Usuario(String id, String nome, String email, String senha, DadosProfissionais dadosProfissionais, Endereco endereco, String cpf, String rg, String orgaoExpedidor, Sexo sexo, Escolaridade escolaridade, String nacionalidade, List<Avaliacao> avaliacoes, String telefone, String celular, String fotoUsuario) {
    	this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dadosProfissionais = dadosProfissionais;
        this.perfil = Perfil.builder().tipoPerfil(TipoPerfil.COMUN).build();
        this.endereco = endereco;
        this.cpf = cpf;
        this.rg = rg;
        this.orgaoExpedidor = orgaoExpedidor;
        this.sexo = sexo;
        this.escolaridade = escolaridade;
        this.nacionalidade = nacionalidade;
        this.avaliacoes = avaliacoes;
        this.telefone = telefone;
        this.celular = celular;
        this.fotoUsuario = fotoUsuario;
    }
    public boolean isAdmin(){
        ;
        if(this.perfils.stream().filter(perfil-> Objects.nonNull(perfil.getTipoPerfil()) && perfil.getTipoPerfil().equals(TipoPerfil.ADMIN)).collect(Collectors.toList()).isEmpty()){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario) {
        	Usuario qualquer = (Usuario) obj;
          return this.id.equals(qualquer.id);
        }else {
          return false;
        }
    }
}
