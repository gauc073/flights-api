package com.apis.flightapis.repositories;


import com.apis.flightapis.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, UUID> {
    @Query(value = "SELECT * from FLIGHT WHERE Origin_Airport = ?1", nativeQuery = true)
    List<FlightEntity> findByOrgAirport(String orgAirport);

    @Query(value = "SELECT * from FLIGHT WHERE Destination_Airport = ?1", nativeQuery = true)
    List<FlightEntity> findByDstAirport(String dstAirport);

    @Query(value = "SELECT * from FLIGHT WHERE Origin_Airport = ?1 AND Destination_Airport = ?2", nativeQuery = true)
    List<FlightEntity> findByJourney(String orgAirport, String dstAirport);
}
