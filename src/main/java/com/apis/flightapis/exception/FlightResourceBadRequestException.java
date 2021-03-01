package com.apis.flightapis.exception;

public class FlightResourceBadRequestException extends Exception {
    private final String traceId;

    public FlightResourceBadRequestException(String traceId, String errorMessage) {
        super(errorMessage);
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
