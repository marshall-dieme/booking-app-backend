package com.spring.airportservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.airportservice.model.Airport;

@Repository
public interface AirportRepo extends JpaRepository<Airport, UUID> {

    Airport findByAirportName(String name);

    @Query(value = "delete a from Airport a where a.airportName=:name")
    void deleteByAirportName(String name);
    
}