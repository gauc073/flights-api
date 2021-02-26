package com.apis.flightapis.service;

import com.apis.flightapis.entity.FlightEntity;
import com.apis.flightapis.exception.LibraryResourceAlreadyExistsException;
import com.apis.flightapis.model.Flight;
import com.apis.flightapis.repositories.FlightRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }
    public Flight addFlight(Flight flightToBeAdded) throws LibraryResourceAlreadyExistsException {

        FlightEntity flightEntity = new FlightEntity(
                flightToBeAdded.getAirlineName(),
                flightToBeAdded.getFlightNumber(),
                flightToBeAdded.getOriginAirport(),
                flightToBeAdded.getOriginAirportCode(),
                flightToBeAdded.getDestinationAirport(),
                flightToBeAdded.getDestinationAirportCode(),
                flightToBeAdded.getDepartureDateAndTime(),
                flightToBeAdded.getArrivalDateAndTime(),
                flightToBeAdded.getCabins(),
                flightToBeAdded.getCabinClass(),
                flightToBeAdded.getNumberOfSeats()
        );

        FlightEntity addedFlightEntity = null;
        try {
            System.out.println(flightEntity);
            addedFlightEntity = flightRepository.save(flightEntity);
        } catch (DataIntegrityViolationException e) {
            throw new LibraryResourceAlreadyExistsException("Flight Already Exists!!");
        }

        flightToBeAdded.setFlightId(addedFlightEntity.getFlightId());
        return flightToBeAdded;
    }
}
