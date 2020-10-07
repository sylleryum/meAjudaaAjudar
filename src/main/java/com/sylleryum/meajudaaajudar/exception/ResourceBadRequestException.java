package com.sylleryum.meajudaaajudar.exception;

public class ResourceBadRequestException extends Exception {

    private String traceId;

    public ResourceBadRequestException(String message, String traceId) {
        super(message);
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
