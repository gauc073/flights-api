package com.apis.flightapis.controller;

import com.apis.flightapis.exception.LibraryResourceAlreadyExistsException;
import com.apis.flightapis.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apis.flightapis.model.Flight;

import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/flights")
public class FlightController {

	private FlightService flightService;

	public FlightController(FlightService flightService) {
		this.flightService = flightService;
	}

	@GetMapping(path = "/{flightId}")
	public Flight getFLight(@PathVariable String flightId) {
		UUID uuid = UUID.fromString(flightId);
		return new Flight(uuid, "airlineName", "flightNumber", "originAirport", "originAirportCode",
				"destinationAirport", "destinationAirportCode", "departureDateAndTime",
				"arrivalDateAndTime", "cabins", "cabinClass", 2);
	}

	@PostMapping
	public ResponseEntity<?> addFlights(@RequestBody Flight flight) {
		try {
			flight =  flightService.addFlight(flight);
		} catch (LibraryResourceAlreadyExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(flight, HttpStatus.CREATED);
	}
	
}
