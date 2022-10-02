package com.spring.flightservice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.flightservice.model.Flight;
import com.spring.flightservice.repository.FlightRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class FlightService {
    private final FlightRepo flightRepo;

    public FlightService(FlightRepo flightRepo) {
        this.flightRepo = flightRepo;
    }

    //retrieve flight List with pagination and size of 20
    public List<Flight> getFlights(int page) {
        //log retrieving page
        log.info("Retrieving page: " + page);
        //try to retrieve 20 flights from the database with pagination and return them as a list
        //or log an error if the database is empty
        try {
            Pageable pageable = PageRequest.of(page, 20);
            return flightRepo.findAll(pageable).getContent();
        } catch (Exception e) {
            log.error("Error retrieving flights from database: " + e.getMessage());
            return null;
        }
    }

    //retrieve flight by flightNumber
    public Flight getFlightByFlightNumber(String flightNumber) {
        //log retrieving flight by flightNumber
        log.info("Retrieving flight by flightNumber: " + flightNumber);
        //try to retrieve flight from the database by flightNumber and return it
        //or log an not found error if the flight is not found
        try {
            return flightRepo.findByFlightNumber(flightNumber);
        } catch (Exception e) {
            log.error("Flight not found: " + e.getMessage());
            return null;
        }
    }

    // create a new flight
    public Flight createFlight(Flight flight) {
        //generate a flight number and assign it to the flight
        flight.setFlightNumber(generateFlightNumber());
        //log creating flight
        log.info("Creating flight: " + flight.getFlightNumber());
        //try to save the flight to the database if
        //flight with same origin, destination, departureDateTime and arrivalDateTime already exists
        //or flight with same flightNumber does not exists
        //or log an error if the flight already exists
        try {
            if (flightRepo.existsByOriginAndDestinationAndDepartureDateTimeAndArrivalDateTime(flight.getOrigin(),
                    flight.getDestination(), flight.getDepartureDateTime(), flight.getArrivalDateTime())) {
                log.error("Flight already exists");
                return null;
            } else if (flightRepo.existsByFlightNumber(flight.getFlightNumber())) {
                log.error("Flight already exists");
                return null;
            } else {
                return flightRepo.save(flight);
            }
        } catch (Exception e) {
            log.error("Error creating flight: " + e.getMessage());
            return null;
        }
    }

    //update flight 
    public Flight updateFlight(Flight flight) {
        //log updating flight
        log.info("Updating flight: " + flight.getFlightNumber());
        //try to update the flight in the database if
        //flight with same origin, destination, departureDateTime and arrivalDateTime already exists
        //or flight with same flightNumber does not exists
        //or log an error if the flight already exists
        try {
            if (flightRepo.existsByOriginAndDestinationAndDepartureDateTimeAndArrivalDateTime(flight.getOrigin(),
                    flight.getDestination(), flight.getDepartureDateTime(), flight.getArrivalDateTime())) {
                log.error("Flight already exists");
                return null;
            } else if (flightRepo.existsByFlightNumber(flight.getFlightNumber())) {
                return flightRepo.save(flight);
            } else {
                log.error("Flight not found");
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating flight: " + e.getMessage());
            return null;
        }
    }

    //delete flight by flightNumber
    public void deleteFlight(String flightNumber) {
        //log deleting flight
        log.info("Deleting flight: " + flightNumber);
        //try to delete the flight from the database by flightNumber
        //or log an error if the flight is not found
        try {
            flightRepo.deleteByFlightNumber(flightNumber);
        } catch (Exception e) {
            log.error("Error deleting flight: " + e.getMessage());
        }
    }

    //generate flight number
    private String generateFlightNumber() {
        //generate a random flight number
        String flightNumber = "FL" + (int) (Math.random() * 1000000);
        //check if the flight number already exists in the database
        //if it does, generate a new flight number
        //else return the flight number
        if (flightRepo.existsByFlightNumber(flightNumber)) {
            return generateFlightNumber();
        } else {
            return flightNumber;
        }
    }
    
}