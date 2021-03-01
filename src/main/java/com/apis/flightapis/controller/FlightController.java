package com.apis.flightapis.controller;

import com.apis.flightapis.exception.FlightResourceAlreadyExistsException;
import com.apis.flightapis.exception.FlightResourceBadRequestException;
import com.apis.flightapis.exception.FlightResourceNotFoundException;
import com.apis.flightapis.model.Flight;
import com.apis.flightapis.service.FlightService;
import com.apis.flightapis.utils.FlightUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping(path = "/{flightId}")
    public ResponseEntity<?> getFLight(@PathVariable String flightId,
                                       @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId)
            throws FlightResourceNotFoundException {
        UUID uuid = UUID.fromString(flightId);
        if (!FlightUtils.doesStringValuePresent(traceId))
            traceId = UUID.randomUUID().toString();
        Flight flight = flightService.getFlight(uuid, traceId);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addFlights(@Valid @RequestBody Flight flight,
                                        @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId)
            throws FlightResourceAlreadyExistsException {
        if (!FlightUtils.doesStringValuePresent(traceId))
            traceId = UUID.randomUUID().toString();
        flightService.addFlight(flight, traceId);
        return new ResponseEntity<>(flight, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{flightId}")
    public ResponseEntity<?> updateFlights(@PathVariable String flightId,
                                           @Valid @RequestBody Flight flight,
                                           @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId)
            throws FlightResourceNotFoundException {
        UUID uuid = UUID.fromString(flightId);
        if (!FlightUtils.doesStringValuePresent(traceId))
            traceId = UUID.randomUUID().toString();
        flight.setFlightId(uuid);
        flightService.updateFlight(flight, traceId);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{flightId}")
    public ResponseEntity<?> deleteFlights(@PathVariable String flightId,
                                           @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId)
            throws FlightResourceNotFoundException {
        UUID uuid = UUID.fromString(flightId);
        if (!FlightUtils.doesStringValuePresent(traceId))
            traceId = UUID.randomUUID().toString();
        flightService.deleteFlight(uuid, traceId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> searchFlights(@RequestParam(required = false) String orgAirport,
                                           @RequestParam(required = false) String dstAirport,
                                           @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId)
            throws FlightResourceBadRequestException {
        if (!FlightUtils.doesStringValuePresent(traceId))
            traceId = UUID.randomUUID().toString();
        if (!FlightUtils.doesStringValuePresent(orgAirport) && !FlightUtils.doesStringValuePresent(dstAirport)) {
            throw new FlightResourceBadRequestException(traceId, "Please provide At-least One search parameter");
        }
        return new ResponseEntity<>(flightService.searchFlights(orgAirport, dstAirport), HttpStatus.OK);
    }

}
