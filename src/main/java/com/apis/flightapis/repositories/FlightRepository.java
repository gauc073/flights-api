package com.apis.flightapis.repositories;


import com.apis.flightapis.entity.FlightEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface FlightRepository extends CrudRepository<FlightEntity, UUID> {
}
