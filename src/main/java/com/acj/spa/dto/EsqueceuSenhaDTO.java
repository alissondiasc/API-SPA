package com.acj.spa.dto;

public class EsqueceuSenhaDTO {

    private String email;
    private String cpf;
    private String senha;

    public EsqueceuSenhaDTO(String email, String cpf) {
        this.email = email;
        this.cpf = cpf;
    }

    public EsqueceuSenhaDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
