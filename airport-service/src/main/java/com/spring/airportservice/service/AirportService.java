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

    public List<Airport> getAll(int page) {
        PageRequest request = PageRequest.of(page, 10);
        return repo.findAll(request).getContent();
    }

    public Airport getAirport(String name) {
        //try to find airport by name or else log airport name not found
        try {
            return repo.findByAirportName(name);
        } catch (Exception e) {
            log.error("Airport {} not found", name);
            return null;
        }
    }

    public Airport createAirport(Airport airport) {
        return repo.save(airport);
    }

    public Airport updateAirport(Airport airport) {
        return repo.save(airport);
    }

    public void delete(String name) {
        repo.deleteByAirportName(name);
    }

}