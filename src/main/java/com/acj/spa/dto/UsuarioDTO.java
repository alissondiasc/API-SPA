package com.acj.spa.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.acj.spa.entities.Avaliacao;
import com.acj.spa.entities.DadosProfissionais;
import com.acj.spa.entities.Endereco;
import com.acj.spa.enums.Escolaridade;
import com.acj.spa.enums.Sexo;

import java.io.Serializable;
import java.util.List;

public class UsuarioDTO implements Serializable {

    private String id;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Formato inválido")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(min = 8, max = 30, message = "A senha deve conter entre 8 e 30 caracteres")
    private String senha;
    
    private DadosProfissionais dadosProfissionais;

    private Endereco endereco;
    
    private boolean isAdmin;
    
    private String cpf;
    
    private String rg;
    
    private String orgaoExpedidor;
    
    private Sexo sexo;
    private Escolaridade escolaridade;
    private String nacionalidade;
    private String telefone;
    private String celular;
    private String fotoUsuario;
    private List<Avaliacao> avaliacaos;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String id, String nome, String email, String senha, DadosProfissionais dadosProfissionais, boolean isAdmin, Endereco endereco,
                      String cpf, String rg,String orgaoExpedidor,Sexo sexo,Escolaridade escolaridade,String nacionalidade, List<Avaliacao> avaliacaos, String telefone, String celular, String fotoUsuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dadosProfissionais = dadosProfissionais;
        this.isAdmin = isAdmin;
        this.endereco = endereco;
        this.cpf = cpf;
        this.rg = rg;
        this.orgaoExpedidor = orgaoExpedidor;
        this.sexo = sexo;
        this.escolaridade = escolaridade;
        this.nacionalidade = nacionalidade;
        this.avaliacaos = avaliacaos;
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

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public DadosProfissionais getDadosProfissionais() {
		return dadosProfissionais;
	}

	public void setDadosProfissionais(DadosProfissionais dadosProfissionais) {
		this.dadosProfissionais = dadosProfissionais;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
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

    public List<Avaliacao> getAvaliacaos() {
        return avaliacaos;
    }

    public void setAvaliacaos(List<Avaliacao> avaliacaos) {
        this.avaliacaos = avaliacaos;
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
}
