package br.com.se.exceptios;

import org.springframework.security.core.AuthenticationException;

public class GenericException extends AuthenticationException {
    public GenericException(String message) {
        super(message);
    }
}
