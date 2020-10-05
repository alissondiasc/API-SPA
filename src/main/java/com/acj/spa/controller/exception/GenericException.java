package com.acj.spa.controller.exception;

import org.springframework.security.core.AuthenticationException;

public class GenericException extends AuthenticationException {
    public GenericException(String message) {
        super(message);
    }
}
