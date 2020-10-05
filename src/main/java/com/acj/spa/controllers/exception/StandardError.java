package com.acj.spa.controllers.exception;

import java.io.Serializable;

public class StandardError implements Serializable {

    private String status;
    private String message;
    private Long timestamp;

    public StandardError() {
    }

    public StandardError(String status, String message, Long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
