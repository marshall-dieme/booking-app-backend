package com.spring.airlineservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.airlineservice.beans.Airline;
import com.spring.airlineservice.services.AirlineService;

import lombok.extern.slf4j.Slf4j;




@RestController
@RequestMapping("/airlines")
@CrossOrigin(origins = "*")
@Slf4j
public class AirlineController {
    private final AirlineService service;

    public AirlineController(AirlineService service) {
        this.service = service;
    }

    //get mapping endpoint to expose list of Airlines with pagination
    @GetMapping
    public List<Airline> getAirlines(@RequestParam(defaultValue = "0") int page) {
        //log retrieving airline page
        log.info("Retrieving airline page: " + page);
        //try to retrieve 20 airlines from the database with pagination and return them as a list
        //or log an error if the database is empty
        try {
            return service.getAirlines(page);
        } catch (Exception e) {
            log.error("Error retrieving airlines from database: " + e.getMessage());
            return null;
        }
    }

    //get mapping endpoint to expose a single airline by airline name
    @GetMapping("/{airlineName}")
    public Airline getAirlineByAirlineName(@PathVariable String airlineName) {
        //log retrieving airline by airlineName
        log.info("Retrieving airline by airlineName: " + airlineName);
        //try to retrieve airline from the database by airlineName and return it
        //or log an not found error if the airline is not found
        try {
            return service.getAirline(airlineName);
        } catch (Exception e) {
            log.error("Airline not found: " + e.getMessage());
            return null;
        }
    }

    // post mapping endpoint to create a new airline
    @PostMapping
    public Airline createAirline(@RequestBody Airline airline) {
        //log creating airline
        log.info("Creating airline: " + airline.getAirlineName());
        //try to create airline and return it
        //or log an error if the airline is not created
        try {
            return service.createAirline(airline);
        } catch (Exception e) {
            log.error("Error creating airline: " + e.getMessage());
            return null;
        }
    }

    // put mapping endpoint to update an existing airline
    @PutMapping
    public Airline updateAirline(@RequestBody Airline airline) {
        //log updating airline
        log.info("Updating airline: " + airline.getAirlineName());
        //try to update airline and return it
        //or log an error if the airline is not updated
        try {
            return service.updateAirline(airline);
        } catch (Exception e) {
            log.error("Error updating airline: " + e.getMessage());
            return null;
        }
    }

    // delete mapping endpoint to delete an existing airline
    @DeleteMapping("/{airlineName}")
    public void deleteAirline(@PathVariable String airlineName) {
        //log deleting airline
        log.info("Deleting airline: " + airlineName);
        //try to delete airline
        //or log an error if the airline is not deleted
        try {
            service.deleteAirline(airlineName);
        } catch (Exception e) {
            log.error("Error deleting airline: " + e.getMessage());
        }
    }
}
