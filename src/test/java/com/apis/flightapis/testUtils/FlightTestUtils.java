package com.apis.flightapis.testUtils;

import com.apis.flightapis.entity.FlightEntity;
import com.apis.flightapis.model.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightTestUtils {
    public static Flight createFlight() {
        return new Flight(null, TestConstants.TEST_FLIGHT_AIRLINE_NAME, TestConstants.TEST_FLIGHT_FLIGHT_NUMBER,
                TestConstants.TEST_FLIGHT_ORIGIN_AIRPORT, TestConstants.TEST_FLIGHT_ORIGIN_AIRPORT_CODE,
                TestConstants.TEST_FLIGHT_DESTINATION_AIRPORT, TestConstants.TEST_FLIGHT_DESTINATION_AIRPORT_CODE,
                TestConstants.TEST_FLIGHT_DEPARTURE_DATE_AND_TIME, TestConstants.TEST_FLIGHT_ARRIVAL_DATE_AND_TIME,
                TestConstants.TEST_FLIGHT_CABINS, TestConstants.TEST_FLIGHT_CABIN_CLASS,
                TestConstants.TEST_FLIGHT_NUMBER_OF_SEATS);
    }

    public static FlightEntity createFlightEntity() {
        FlightEntity flightEntity = new FlightEntity(TestConstants.TEST_FLIGHT_AIRLINE_NAME, TestConstants.TEST_FLIGHT_FLIGHT_NUMBER,
                TestConstants.TEST_FLIGHT_ORIGIN_AIRPORT, TestConstants.TEST_FLIGHT_ORIGIN_AIRPORT_CODE,
                TestConstants.TEST_FLIGHT_DESTINATION_AIRPORT, TestConstants.TEST_FLIGHT_DESTINATION_AIRPORT_CODE,
                TestConstants.TEST_FLIGHT_DEPARTURE_DATE_AND_TIME, TestConstants.TEST_FLIGHT_ARRIVAL_DATE_AND_TIME,
                TestConstants.TEST_FLIGHT_CABINS, TestConstants.TEST_FLIGHT_CABIN_CLASS,
                TestConstants.TEST_FLIGHT_NUMBER_OF_SEATS);
        flightEntity.setFlightId(TestConstants.TEST_FLIGHT_UUID);
        return flightEntity;
    }

    public static List<FlightEntity> createFlightList() {
        List<FlightEntity> flightList = new ArrayList<>();
        flightList.add(createFlightEntity());
        return flightList;
    }

    public static Optional<FlightEntity> createPublisherEntityOptional() {
        return Optional.of(createFlightEntity());
    }
}
