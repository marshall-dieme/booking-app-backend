package com.spring.airportservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.airportservice.model.Airport;
import com.spring.airportservice.service.AirportService;

import java.util.List;
import java.util.UUID;

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
public class AirportController {
    private final AirportService service;


    public AirportController(AirportService service) {
        this.service = service;
    }

    @GetMapping
    public List<Airport> getAll(@RequestParam(defaultValue = "0") int page) {
        return service.getAll(page);
    }
    
    @GetMapping(value = "/{name}")
    public Airport getByName(@RequestParam String name) {
        return service.getAirport(name);
    }

    @PostMapping
    public Airport createAirport(@RequestBody @Validated Airport airport) {
        return service.createAirport(airport);
    }
    
    @PutMapping
    public Airport editAirport(@RequestBody @Validated Airport airport) {
        return service.updateAirport(airport);
    }

    @DeleteMapping("/{name}")
    public Void delete(@PathVariable String name) {
        service.delete(name);
        return null;
    }
}