package com.apis.flightapis.exception;

import com.apis.flightapis.utils.FlightUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class FlightControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FlightResourceNotFoundException.class)
    public final ResponseEntity<FlightsError> handleFlightResourceNotFoundException(
            FlightResourceNotFoundException e, WebRequest webRequest) {
        return new ResponseEntity<>(new FlightsError(e.getTraceId(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlightResourceAlreadyExistsException.class)
    public final ResponseEntity<FlightsError> handleFlightResourceAlreadyExistsException(
            FlightResourceAlreadyExistsException e, WebRequest webRequest) {
        return new ResponseEntity<>(new FlightsError(e.getTraceId(), e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FlightResourceBadRequestException.class)
    public final ResponseEntity<FlightsError> handleFlightResourceBadRequestException(
            FlightResourceBadRequestException e, WebRequest webRequest) {
        return new ResponseEntity<>(new FlightsError(e.getTraceId(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<FlightsError> handleGenericException(
            Exception e, WebRequest webRequest) {
        String traceId = getTraceId(webRequest);
        return new ResponseEntity<>(new FlightsError(traceId, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private String getTraceId(WebRequest webRequest) {
        String traceId = webRequest.getHeader("Trace-Id");
        if (!FlightUtils.doesStringValuePresent(traceId))
            return UUID.randomUUID().toString();
        return traceId;
    }
}
