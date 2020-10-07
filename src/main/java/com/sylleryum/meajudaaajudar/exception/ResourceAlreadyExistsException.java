package com.sylleryum.meajudaaajudar.exception;

public class ResourceAlreadyExistsException extends Exception {

    private String traceId;

    public ResourceAlreadyExistsException(String message, String traceId) {
        super(message);
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
