package com.sylleryum.meajudaaajudar.exception;

public class IlegalCredentialsException {

    private String traceId;
    private String error;

    public IlegalCredentialsException() {
    }

    public IlegalCredentialsException(String traceId, String error) {
        this.traceId = traceId;
        this.error = error;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
