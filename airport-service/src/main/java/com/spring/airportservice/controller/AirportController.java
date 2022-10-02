package com.spring.airportservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.airportservice.model.Airport;
import com.spring.airportservice.service.AirportService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/airports")
@Slf4j
public class AirportController {
    private final AirportService service;


    public AirportController(AirportService service) {
        this.service = service;
    }

    //get all airports with pagination and size of 10
    @GetMapping
    public List<Airport> getAllAirports(@RequestParam(defaultValue = "0") int page) {
        //log message to get all airports with page number
        log.info("Request to get all airports with page number: " + page);
        //get all airports and return it
        return service.getAllAirports(page);
    }

    //get airport by name
    @GetMapping("/{name}")
    public Airport getAirport(@PathVariable String name) {
        //log message to get airport with name
        log.info("Request to get airport with name: " + name);
        //get airport by name and return it
        return service.getAirport(name);
    }

    //add new airport
    @PostMapping
    public Airport addAirport(@Validated @RequestBody Airport airport) {
        //log message to add airport
        log.info("Request to add airport: " + airport.getName());
        //add airport and return it
        return service.addAirport(airport);
    }

    //update airport
    @PutMapping
    public Airport updateAirport(@Validated @RequestBody Airport airport) {
        //log message to update airport
        log.info("Request to update airport: " + airport.getName());
        //update airport and return it
        return service.updateAirport(airport);
    }

    //delete airport by name
    @DeleteMapping("/{name}")
    public void deleteAirport(@PathVariable String name) {
        //log message to delete airport
        log.info("Request to delete airport with name: " + name);
        //delete airport by name
        service.deleteAirport(name);
    }
}