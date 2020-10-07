package com.sylleryum.meajudaaajudar.exception;

public class ResourceNotFoundException extends Exception{

    private String traceId;

    public ResourceNotFoundException(String traceId, String message) {
        super(message);
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
