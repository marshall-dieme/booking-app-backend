package com.spring.airportservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.airportservice.model.Airport;

@Repository
public interface AirportRepo extends JpaRepository<Airport, UUID> {

    Optional<Airport> findByName(String name);
    
}