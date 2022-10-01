package com.spring.flightservice.repository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.flightservice.model.Flight;

@Repository
public interface FlightRepo extends JpaRepository<Flight, UUID> {

    Flight findByFlightNumber(String flightNumber);

    boolean existsByOriginAndDestinationAndDepartureDateTimeAndArrivalDateTime(String origin, String destination,
            LocalDateTime departureDateTime, LocalDateTime arrivalDateTime);

    boolean existsByFlightNumber(String flightNumber);

    @Query("DELETE FROM Flight f WHERE f.flightNumber = ?1")
    void deleteByFlightNumber(String flightNumber);

}