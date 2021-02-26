package com.apis.flightapis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apis.flightapis.model.Flight;

@RestController
@RequestMapping(path = "/v1/flights")
public class FlightController {
	
	@GetMapping(path = "/{flightId}")
	public Flight getFLight(@PathVariable String flightId) {
		return new Flight(flightId, "airlineName", "flightNumber", "originAirport", "originAirportCode",
				"destinationAirport", "destinationAirportCode", "departureDateAndTime",
				"arrivalDateAndTime", "cabins", "cabinClass", 2);
	}
	
}
