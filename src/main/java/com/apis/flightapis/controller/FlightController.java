package com.apis.flightapis.controller;

import com.apis.flightapis.exception.LibraryResourceAlreadyExistsException;
import com.apis.flightapis.exception.LibraryResourceNotFoundException;
import com.apis.flightapis.model.Flight;
import com.apis.flightapis.service.FlightService;
import com.apis.flightapis.utils.FlightUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping(path = "/{flightId}")
    public ResponseEntity<?> getFLight(@PathVariable String flightId) {
        UUID uuid = UUID.fromString(flightId);
        try {
            Flight flight = flightService.getFlight(uuid);
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } catch (LibraryResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addFlights(@RequestBody Flight flight) {
        try {
            flightService.addFlight(flight);
        } catch (LibraryResourceAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(flight, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{flightId}")
    public ResponseEntity<?> updateFlights(@PathVariable String flightId, @RequestBody Flight flight) {
        UUID uuid = UUID.fromString(flightId);
        try {
            flight.setFlightId(uuid);
            flightService.updateFlight(flight);
        } catch (LibraryResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{flightId}")
    public ResponseEntity<?> deleteFlights(@PathVariable String flightId) {
        UUID uuid = UUID.fromString(flightId);
        try {
            flightService.deleteFlight(uuid);
        } catch (LibraryResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> searchFlights(@RequestParam(required = false) String orgAirport,
                                           @RequestParam(required = false) String dstAirport) {
        if (!FlightUtils.doesStringValuePresent(orgAirport) && !FlightUtils.doesStringValuePresent(dstAirport)) {
            return new ResponseEntity<>("Please provide At-least One search parameter", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(flightService.searchFlights(orgAirport, dstAirport), HttpStatus.OK);
    }

}
