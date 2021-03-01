package com.apis.flightapis.service;

import com.apis.flightapis.entity.FlightEntity;
import com.apis.flightapis.exception.FlightResourceAlreadyExistsException;
import com.apis.flightapis.exception.FlightResourceNotFoundException;
import com.apis.flightapis.model.Flight;
import com.apis.flightapis.repositories.FlightRepository;
import com.apis.flightapis.utils.FlightUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final Logger logger = LoggerFactory.getLogger(FlightService.class);

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    private static void updateRecord(FlightEntity flightEntity, Flight flight) {
        if (FlightUtils.doesStringValuePresent(flight.getAirlineName())) {
            flightEntity.setAirlineName(flight.getAirlineName());
        }
        if (FlightUtils.doesStringValuePresent(flight.getFlightNumber())) {
            flightEntity.setFlightNumber(flight.getFlightNumber());
        }
        if (FlightUtils.doesStringValuePresent(flight.getOriginAirport())) {
            flightEntity.setOriginAirport(flight.getOriginAirport());
        }
        if (FlightUtils.doesStringValuePresent(flight.getOriginAirportCode())) {
            flightEntity.setOriginAirportCode(flight.getOriginAirportCode());
        }
        if (FlightUtils.doesStringValuePresent(flight.getDestinationAirport())) {
            flightEntity.setDestinationAirport(flight.getDestinationAirport());
        }
        if (FlightUtils.doesStringValuePresent(flight.getDestinationAirportCode())) {
            flightEntity.setDestinationAirportCode(flight.getDestinationAirportCode());
        }
        if (FlightUtils.doesStringValuePresent(flight.getDepartureDateAndTime())) {
            flightEntity.setDepartureDateAndTime(flight.getDepartureDateAndTime());
        }
        if (FlightUtils.doesStringValuePresent(flight.getArrivalDateAndTime())) {
            flightEntity.setArrivalDateAndTime(flight.getArrivalDateAndTime());
        }
        if (FlightUtils.doesStringValuePresent(flight.getCabins())) {
            flightEntity.setCabins(flight.getCabins());
        }
        flightEntity.setCabinClass(flight.getCabinClass());
        flightEntity.setNumberOfSeats(flight.getNumberOfSeats());
    }

    public Flight getFlight(UUID flightId, String traceId) throws FlightResourceNotFoundException {

        FlightEntity flightEntity;
        Optional<FlightEntity> optional = flightRepository.findById(flightId);
        if (optional.isPresent()) {
            flightEntity = optional.get();
        } else {
            logger.error("TraceId: {}, Flight Does not Found!!", traceId);
            throw new FlightResourceNotFoundException(traceId, "Flight Does not Found!!");
        }
        return flightFromFlightEntity(flightEntity);

    }

    private Flight flightFromFlightEntity(FlightEntity flightEntity) {
        Flight flight = new Flight();
        flight.setFlightId(flightEntity.getFlightId());
        flight.setAirlineName(flightEntity.getAirlineName());
        flight.setFlightNumber(flightEntity.getFlightNumber());
        flight.setOriginAirport(flightEntity.getOriginAirport());
        flight.setOriginAirportCode(flightEntity.getOriginAirportCode());
        flight.setDestinationAirport(flightEntity.getDestinationAirport());
        flight.setDestinationAirportCode(flightEntity.getDestinationAirportCode());
        flight.setDepartureDateAndTime(flightEntity.getDepartureDateAndTime());
        flight.setArrivalDateAndTime(flightEntity.getArrivalDateAndTime());
        flight.setCabins(flightEntity.getCabins());
        flight.setCabinClass(flightEntity.getCabinClass());
        flight.setNumberOfSeats(flightEntity.getNumberOfSeats());
        return flight;
    }

    public void addFlight(Flight flightToBeAdded, String traceId) throws FlightResourceAlreadyExistsException {

        FlightEntity flightEntity = new FlightEntity(
                flightToBeAdded.getAirlineName(), flightToBeAdded.getFlightNumber(),
                flightToBeAdded.getOriginAirport(), flightToBeAdded.getOriginAirportCode(),
                flightToBeAdded.getDestinationAirport(), flightToBeAdded.getDestinationAirportCode(),
                flightToBeAdded.getDepartureDateAndTime(), flightToBeAdded.getArrivalDateAndTime(),
                flightToBeAdded.getCabins(), flightToBeAdded.getCabinClass(),
                flightToBeAdded.getNumberOfSeats()
        );

        FlightEntity addedFlightEntity;
        try {
            addedFlightEntity = flightRepository.save(flightEntity);
        } catch (DataIntegrityViolationException e) {
            logger.error("TraceId: {}, {}", traceId, e.getMessage());
            throw new FlightResourceAlreadyExistsException(traceId, "Flight Already Exists!!");
        }

        flightToBeAdded.setFlightId(addedFlightEntity.getFlightId());
    }

    public void updateFlight(Flight flightToBeUpdated, String traceId) throws FlightResourceNotFoundException {
        Optional<FlightEntity> optionalFlightEntity = flightRepository.findById(flightToBeUpdated.getFlightId());
        try {
            if (optionalFlightEntity.isPresent()) {
                FlightEntity flightEntity = optionalFlightEntity.get();
                FlightService.updateRecord(flightEntity, flightToBeUpdated);
                flightRepository.save(flightEntity);
                flightToBeUpdated = flightFromFlightEntity(flightEntity);
                logger.info("TraceId: {}, Flight ID-> {} Updated", traceId, flightToBeUpdated.getFlightId());
            } else {
                logger.error("TraceId: {}, Flight ID-> {} Not Found", traceId, flightToBeUpdated.getFlightId());
                throw new FlightResourceNotFoundException(traceId, "Flight ID-> " + flightToBeUpdated.getFlightId() + " Not Found");
            }
        } catch (Exception e) {
            logger.error("TraceId:  {}, {}", traceId, e.getMessage());
            throw new FlightResourceNotFoundException(traceId, e.getMessage());
        }
    }

    public void deleteFlight(UUID flightId, String traceId) throws FlightResourceNotFoundException {
        try {
            flightRepository.deleteById(flightId);
        } catch (EmptyResultDataAccessException e) {
            logger.error("TraceId: {}, Flight ID-> {} Not Found", traceId, flightId);
            throw new FlightResourceNotFoundException(traceId, "Flight ID-> " + flightId + " Not Found");
        }
    }

    public List<Flight> searchFlights(String orgAirport, String dstAirport) {
        List<FlightEntity> flightEntityList;
        if (!FlightUtils.doesStringValuePresent(orgAirport))
            flightEntityList = flightRepository.findByDstAirport(dstAirport);
        else if (!FlightUtils.doesStringValuePresent(dstAirport))
            flightEntityList = flightRepository.findByOrgAirport(orgAirport);
        else flightEntityList = flightRepository.findByJourney(orgAirport, dstAirport);
        if (flightEntityList != null && flightEntityList.size() > 0) {
            return flightListFromFlightEntityList(flightEntityList);
        }
        return Collections.emptyList();
    }

    private List<Flight> flightListFromFlightEntityList(List<FlightEntity> flightEntityList) {
        return flightEntityList.stream().map(this::flightFromFlightEntity).collect(Collectors.toList());

    }
}
