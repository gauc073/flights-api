package com.apis.flightapis.service;

import com.apis.flightapis.entity.FlightEntity;
import com.apis.flightapis.exception.FlightResourceAlreadyExistsException;
import com.apis.flightapis.exception.FlightResourceNotFoundException;
import com.apis.flightapis.model.Flight;
import com.apis.flightapis.repositories.FlightRepository;
import com.apis.flightapis.testUtils.FlightTestUtils;
import com.apis.flightapis.testUtils.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class FlightServiceTest {
    FlightRepository flightRepository;

    FlightService flightService;

    @BeforeEach
    void setUp() {
        flightRepository = mock(FlightRepository.class);
        flightService = new FlightService(flightRepository);
    }

    @Test
    void getFlightSuccess() throws FlightResourceNotFoundException {
        when(flightRepository.findById(TestConstants.TEST_FLIGHT_UUID))
                .thenReturn(FlightTestUtils.createPublisherEntityOptional());
        Flight flight = flightService.getFlight(TestConstants.TEST_FLIGHT_UUID, TestConstants.TEST_FLIGHT_TRACE_ID);
        verify(flightRepository, times(1)).findById(TestConstants.TEST_FLIGHT_UUID);
        assertNotNull(flight.getFlightId());
        assertThat(flight.getAirlineName()).isEqualTo(TestConstants.TEST_FLIGHT_AIRLINE_NAME);
    }

    @Test
    void getFlightFailure() {
        when(flightRepository.findById(TestConstants.TEST_FLIGHT_UUID))
                .thenReturn(Optional.empty());
        Throwable thrown = catchThrowable(() ->
                flightService.getFlight(TestConstants.TEST_FLIGHT_UUID, TestConstants.TEST_FLIGHT_TRACE_ID)
        );
        verify(flightRepository, times(1)).findById(TestConstants.TEST_FLIGHT_UUID);
        assertThat(thrown).isInstanceOf(FlightResourceNotFoundException.class);
        assertThat(thrown.getMessage()).isEqualTo("Flight Does not Found!!");
    }

    @Test
    void addFlightSuccess() throws FlightResourceAlreadyExistsException {
        when(flightRepository.save(any(FlightEntity.class)))
                .thenReturn(FlightTestUtils.createFlightEntity());
        Flight flight = FlightTestUtils.createFlight();
        flightService.addFlight(flight, TestConstants.TEST_FLIGHT_FLIGHT_ID);
        verify(flightRepository, times(1)).save(any(FlightEntity.class));
        assertNotNull(flight.getFlightId());
        assertThat(flight.getAirlineName()).isEqualTo(TestConstants.TEST_FLIGHT_AIRLINE_NAME);
    }

    @Test
    void addFlightFailure() {
        doThrow(DataIntegrityViolationException.class).when(flightRepository).save(any(FlightEntity.class));
        Flight flight = FlightTestUtils.createFlight();
        Throwable thrown = catchThrowable(() ->
                flightService.addFlight(flight, TestConstants.TEST_FLIGHT_FLIGHT_ID)
        );
        verify(flightRepository, times(1)).save(any(FlightEntity.class));
        assertThat(thrown).isInstanceOf(FlightResourceAlreadyExistsException.class);
        assertThat(thrown.getMessage()).isEqualTo("Flight Already Exists!!");
    }

    @Test
    void updateFlightSuccess() throws FlightResourceNotFoundException {
        when(flightRepository.findById(TestConstants.TEST_FLIGHT_UUID))
                .thenReturn(FlightTestUtils.createPublisherEntityOptional());
        when(flightRepository.save(any(FlightEntity.class)))
                .thenReturn(FlightTestUtils.createFlightEntity());
        Flight flight = FlightTestUtils.createFlight();
        flight.setFlightId(TestConstants.TEST_FLIGHT_UUID);
        flight.setAirlineName("new Airline");
        flightService.updateFlight(flight, TestConstants.TEST_FLIGHT_TRACE_ID);
        verify(flightRepository, times(1)).findById(TestConstants.TEST_FLIGHT_UUID);
        verify(flightRepository, times(1)).save(any(FlightEntity.class));
        assertNotNull(flight.getFlightId());
        assertThat(flight.getAirlineName()).isEqualTo("new Airline");
    }

    @Test
    void updateFlightFailure() {
        when(flightRepository.findById(TestConstants.TEST_FLIGHT_UUID))
                .thenReturn(Optional.empty());
        Flight flight = FlightTestUtils.createFlight();
        flight.setFlightId(TestConstants.TEST_FLIGHT_UUID);
        Throwable thrown = catchThrowable(() ->
                flightService.updateFlight(flight, TestConstants.TEST_FLIGHT_TRACE_ID)
        );
        verify(flightRepository, times(1)).findById(TestConstants.TEST_FLIGHT_UUID);
        assertThat(thrown).isInstanceOf(FlightResourceNotFoundException.class);
        assertThat(thrown.getMessage()).isEqualTo("Flight ID-> 44444444-4444-4444-4444-444444444444 Not Found");
    }

    @Test
    void deleteFlightSuccess() throws FlightResourceNotFoundException {
        when(flightRepository.findById(TestConstants.TEST_FLIGHT_UUID))
                .thenReturn(FlightTestUtils.createPublisherEntityOptional());
        flightService.deleteFlight(TestConstants.TEST_FLIGHT_UUID, TestConstants.TEST_FLIGHT_TRACE_ID);
        verify(flightRepository, times(1)).deleteById(TestConstants.TEST_FLIGHT_UUID);
    }

    @Test
    void deleteFlightFailure() {
        doThrow(EmptyResultDataAccessException.class).when(flightRepository).deleteById(TestConstants.TEST_FLIGHT_UUID);
        Throwable thrown = catchThrowable(() ->
                flightService.deleteFlight(TestConstants.TEST_FLIGHT_UUID, TestConstants.TEST_FLIGHT_TRACE_ID)
        );

        verify(flightRepository, times(1)).deleteById(TestConstants.TEST_FLIGHT_UUID);
        assertThat(thrown).isInstanceOf(FlightResourceNotFoundException.class);
        assertThat(thrown.getMessage()).isEqualTo("Flight ID-> 44444444-4444-4444-4444-444444444444 Not Found");
    }

    @Test
    void searchFlightsSuccess() {
        when(flightRepository.findByJourney(TestConstants.TEST_FLIGHT_ORIGIN_AIRPORT, TestConstants.TEST_FLIGHT_DESTINATION_AIRPORT))
                .thenReturn(FlightTestUtils.createFlightList());
        List<Flight> flightList = flightService.searchFlights(TestConstants.TEST_FLIGHT_ORIGIN_AIRPORT, TestConstants.TEST_FLIGHT_DESTINATION_AIRPORT);
        assertThat(flightList.size()).isEqualTo(1);
        assertThat(flightList.get(0).getFlightId()).isEqualTo(TestConstants.TEST_FLIGHT_UUID);
    }

    @Test
    void searchFlightsFailure() {
        when(flightRepository.findByJourney(TestConstants.TEST_FLIGHT_ORIGIN_AIRPORT, TestConstants.TEST_FLIGHT_DESTINATION_AIRPORT))
                .thenReturn(new ArrayList<>());
        List<Flight> flightList = flightService.searchFlights(TestConstants.TEST_FLIGHT_ORIGIN_AIRPORT, TestConstants.TEST_FLIGHT_DESTINATION_AIRPORT);
        assertThat(flightList.size()).isEqualTo(0);
    }
}