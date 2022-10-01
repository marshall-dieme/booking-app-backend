package com.spring.flightservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.flightservice.model.Flight;
import com.spring.flightservice.service.FlightService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/flights")
@Slf4j
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    //retrieve flight List with pagination
    @GetMapping
    public List<Flight> getFlights(@RequestParam(value = "page", defaultValue = "0") int page) {
        //log retrieving page
        log.info("Retrieving page: " + page);
        //try to retrieve 20 flights from the database with pagination and return them as a list
        //or log an error if the database is empty
        try {
            return flightService.getFlights(page);
        } catch (Exception e) {
            log.error("Error retrieving flights from database: " + e.getMessage());
            return null;
        }
    }

    //retrieve flight by flightNumber
    @GetMapping("/{flightNumber}")
    public Flight getFlightByFlightNumber(@PathVariable String flightNumber) {
        //log retrieving flight by flightNumber
        log.info("Retrieving flight by flightNumber: " + flightNumber);
        //try to retrieve flight from the database by flightNumber and return it
        //or log an not found error if the flight is not found
        try {
            return flightService.getFlightByFlightNumber(flightNumber);
        } catch (Exception e) {
            log.error("Flight not found: " + e.getMessage());
            return null;
        }
    }

    // create a new flight
    @PostMapping
    public Flight createFlight(Flight flight) {
        // log creating new flight
        log.info("Creating new flight...");
        // try to create a new flight and return it
        // or log an error if the flight is not created
        try {
            return flightService.createFlight(flight);
        } catch (Exception e) {
            log.error("Error creating flight: " + e.getMessage());
            return null;
        }
    }

    // update a flight
    @PutMapping
    public Flight updateFlight(Flight flight) {
        // log updating flight number
        log.info("Updating flight number: " + flight.getFlightNumber());
        // try to update the flight and return it
        // or log an error if the flight is not updated
        try {
            return flightService.updateFlight(flight);
        } catch (Exception e) {
            log.error("Error updating flight: " + e.getMessage());
            return null;
        }
    }

    // delete a flight by flightNumber
    @DeleteMapping
    public void deleteFlightByFlightNumber(@RequestParam String flightNumber) {
        // log deleting flight by flightNumber
        log.info("Deleting flight by flightNumber: " + flightNumber);
        // try to delete the flight by flightNumber
        // or log an error if the flight is not deleted
        try {
            flightService.deleteFlight(flightNumber);
        } catch (Exception e) {
            log.error("Error deleting flight: " + e.getMessage());
        }
    }
}