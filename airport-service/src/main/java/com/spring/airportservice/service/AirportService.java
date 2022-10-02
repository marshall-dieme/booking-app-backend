package com.spring.airportservice.service;

import java.util.List;

import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.spring.airportservice.model.Airport;
import com.spring.airportservice.repository.AirportRepo;

@Service
@Transactional
@Slf4j
public class AirportService {
    private final AirportRepo repo;


    public AirportService(AirportRepo repo) {
        this.repo = repo;
    }

    //get all airports with pagination and size of 10
    public List<Airport> getAllAirports(int page) {
        // log the request
        log.info("Request to get all airports");
        //get the airport list and return it if it's not empty
        //or log a empty database message
        List<Airport> airports = repo.findAll(PageRequest.of(page, 10)).getContent();
        if (!airports.isEmpty()) {
            log.info("Returning all airports");
            return airports;
        } else {
            log.info("Database is empty");
            return null;
        }
    }
    
    //get airport by name
    public Airport getAirport(String name) {
        // log retrieving airport name
        log.info("Request to get airport with name: " + name);
        //try to find airport by name and return it or log not found error
        return repo.findByName(name).orElseThrow(() -> {
            log.error("Airport with name: " + name + " not found");
            return new RuntimeException("Airport with name: " + name + " not found");
        });
    }

    //add new airport
    public Airport addAirport(Airport airport) {
        // log adding airport
        log.info("Request to add airport: " + airport.getName());
        //check if airport already exists and return it or save it and return it
        return repo.findByName(airport.getName()).orElseGet(() -> {
            log.info("Saving airport: " + airport.getName());
            return repo.save(airport);
        });
    }

    //update airport
    public Airport updateAirport(Airport airport) {
        // log updating airport
        log.info("Request to update airport: " + airport.getName());
        //check if airport exists and update it or log not found error
        return repo.findByName(airport.getName()).map(a -> {
            log.info("Updating airport: " + airport.getName());
            a.setIata(airport.getIata());
            a.setIcao(airport.getIcao());
            a.setCity(airport.getCity());
            a.setCountry(airport.getCountry());
            return repo.save(a);
        }).orElseThrow(() -> {
            log.error("Airport with name: " + airport.getName() + " not found");
            return new RuntimeException("Airport with name: " + airport.getName() + " not found");
        });
    }

    //delete airport by name
    public void deleteAirport(String name) {
        // log deleting airport
        log.info("Request to delete airport with name: " + name);
        //check if airport exists and delete it or log not found error
        repo.findByName(name).ifPresentOrElse(a -> {
            log.info("Deleting airport: " + name);
            repo.delete(a);
        }, () -> {
            log.error("Airport with name: " + name + " not found");
            throw new RuntimeException("Airport with name: " + name + " not found");
        });
    }
}