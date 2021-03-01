package com.apis.flightapis.exception;

public class FlightResourceAlreadyExistsException extends Exception {

    private final String traceId;

    public FlightResourceAlreadyExistsException(String traceId, String errorMessage) {
        super(errorMessage);
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
