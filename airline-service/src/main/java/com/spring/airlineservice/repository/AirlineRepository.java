package com.spring.airlineservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import com.spring.airlineservice.beans.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, UUID> {
    @Query(value="delete from airline where airline_name=:airlineName", nativeQuery = true)
    void deleteByAirlineName(String airlineName);

    Optional<Airline> findByAirlineName(String airlineName);
}