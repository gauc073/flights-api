package com.apis.flightapis.exception;

public class FlightResourceNotFoundException extends Exception {
    private final String traceId;

    public FlightResourceNotFoundException(String traceId, String errorMessage) {
        super(errorMessage);
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
