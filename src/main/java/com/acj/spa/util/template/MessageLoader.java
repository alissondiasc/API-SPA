package com.acj.spa.util.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static java.util.Objects.nonNull;

public class MessageLoader implements Serializable {

    private static final long serialVersionUID = 1537892670160655386L;

    private static final String MSG_ERRO_INTERNO_REQUISICAO = "msg.erro.interno.requisicao";
    private static final String MSG_ERRO_NAO_ENCONTRADO = "msg.erro.interno.nao.encontro";

    private static Logger logger = LoggerFactory.getLogger(MessageLoader.class);
    private static final ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle("messagens", new Locale("pt", "BR"));
    }

    public static String getMessage(final String message) {
        try {
            final String msg = nonNull(message) ? message : MSG_ERRO_INTERNO_REQUISICAO;
            return bundle.getString(msg);
        } catch (MissingResourceException e) {
            logger.error(e.getMessage(), e);
        }

        return bundle.getString(MSG_ERRO_INTERNO_REQUISICAO);
    }

    /**
     * Recuperar a mensagem pela quantidade de values informado.
     * Ex:
     * Nova Mensagem - value {0}, value [1}
     * Método deverá receber caminho da mensagem e dois paramentros esperado.
     *
     * @param message
     * @param values
     * @return mensagens recuperada com valores informados.
     */
    public static String getMessage(final String message, final Object... values) {
        String msgErro = getMessage(message);
        return nonNull(values) ? processarValores(msgErro, values) : getMessage(MSG_ERRO_INTERNO_REQUISICAO);
    }

    public static String getMsgNaoEncontrado(final String iniMsg) {
        return getMessage(MSG_ERRO_NAO_ENCONTRADO, iniMsg);
    }

    public static String getMsgNaoInformado(final String iniMsg) {
        return getMessage("msg.erro.interno.nao.informado", iniMsg);
    }


    private static String processarValores(String msgErro, Object[] values) {
        for (int pos = 0; pos < values.length; pos++) {
            String target = "{" + pos + "}";
            msgErro = msgErro.replace(target, getValue(values[pos]));
        }

        return msgErro;
    }

    private static String getValue(Object value) {
        return nonNull(value) ? (value).toString() : "null";
    }

    public static String getMsgException(Exception e) {
        return nonNull(e.getMessage()) ? e.getMessage() : getMessage(null);
    }

    public static ResponseEntity msgSucesso(final String message) {
        return ResponseEntity.ok(message);
    }

    public static ResponseEntity msgErro(final String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    public static ResponseEntity msgErro(final String message, final HttpStatus status) {
        return ResponseEntity.status(status).body(message);
    }


}
