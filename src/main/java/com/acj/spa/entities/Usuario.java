package com.acj.spa.entities;

import com.acj.spa.enums.Escolaridade;
import com.acj.spa.enums.Perfil;
import com.acj.spa.enums.Sexo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document
public class Usuario implements Serializable {

    @Id
    private String id;
    private String nome;
    @Indexed(unique = true)
    private String email;
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

	public Usuario() {
    }

    public Usuario(String id, String nome, String email, String senha, DadosProfissionais dadosProfissionais, Endereco endereco,String cpf, String rg, String orgaoExpedidor,Sexo sexo,Escolaridade escolaridade,String nacionalidade, List<Avaliacao> avaliacoes, String telefone, String celular, String fotoUsuario) {
    	this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dadosProfissionais = dadosProfissionais;
        this.perfil = Perfil.ROLE_USER;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public DadosProfissionais getDadosProfissionais() {
        return dadosProfissionais;
    }

    public void setDadosProfissionais(DadosProfissionais dadosProfissionais) {
        this.dadosProfissionais = dadosProfissionais;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public boolean isAdmin() {
		return this.perfil.equals(Perfil.ROLE_ADMIN);
	}

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
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
