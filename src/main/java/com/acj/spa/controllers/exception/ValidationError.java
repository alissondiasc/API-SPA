package com.acj.spa.controllers.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<CampoMensagem> erros = new ArrayList<>();

    public ValidationError(String status, String message, Long timestamp) {
        super(status, message, timestamp);
    }

    public List<CampoMensagem> getErros() {
        return erros;
    }

    public void addErro(String campo, String mensagem) {
        this.erros.add(new CampoMensagem(campo, mensagem));
    }
}
