package com.apis.flightapis.exception;

public class FlightsError {
    private String traceId;
    private String errorMessage;

    public FlightsError(String traceId, String errorMessage) {
        this.traceId = traceId;
        this.errorMessage = errorMessage;
    }

    public FlightsError() {
    }

    @Override
    public String toString() {
        return "FlightsError{" +
                "traceId='" + traceId + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
