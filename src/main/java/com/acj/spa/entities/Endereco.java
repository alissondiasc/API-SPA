package com.acj.spa.entities;

import java.io.Serializable;

public class Endereco implements Serializable {

	private String cep;
	private String rua;
	private String bairro;
	private String localidade;
	private String logradouro;
	private String ibge;
	private String uf;

	public Endereco() {
	}

	public Endereco(String cep, String rua, String bairro, String localidade, String logradouro, String ibge, String uf) {
		this.cep = cep;
		this.rua = rua;
		this.bairro = bairro;
		this.localidade = localidade;
		this.logradouro = logradouro;
		this.ibge = ibge;
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
