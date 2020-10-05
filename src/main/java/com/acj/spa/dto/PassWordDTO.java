package com.acj.spa.dto;

public class PassWordDTO {
    private String  antiga;
    private String  nova;

    public PassWordDTO(String antiga, String nova) {
        this.antiga = antiga;
        this.nova = nova;
    }

    public PassWordDTO() {
    }

    public String getAntiga() {
        return antiga;
    }

    public void setAntiga(String antiga) {
        this.antiga = antiga;
    }

    public String getNova() {
        return nova;
    }

    public void setNova(String nova) {
        this.nova = nova;
    }
}
