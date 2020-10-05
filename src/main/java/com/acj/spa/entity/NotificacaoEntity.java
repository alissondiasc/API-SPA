package com.acj.spa.entity;

public class NotificacaoEntity {
    private String idUsuario;
    private String tituloNotificacao;
    private String subTituloNotificacao;
    private Boolean visto = false;


    public NotificacaoEntity() {
    }

    public NotificacaoEntity(String idUsuario, String tituloNotificacao, String subTituloNotificacao) {
        this.idUsuario = idUsuario;
        this.tituloNotificacao = tituloNotificacao;
        this.subTituloNotificacao = subTituloNotificacao;
        this.visto = visto;
    }
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTituloNotificacao() {
        return tituloNotificacao;
    }

    public void setTituloNotificacao(String tituloNotificacao) {
        this.tituloNotificacao = tituloNotificacao;
    }

    public String getSubTituloNotificacao() {
        return subTituloNotificacao;
    }

    public void setSubTituloNotificacao(String subTituloNotificacao) {
        this.subTituloNotificacao = subTituloNotificacao;
    }

    public Boolean getVisto() {
        return visto;
    }

    public void setVisto(Boolean visto) {
        this.visto = visto;
    }
}
