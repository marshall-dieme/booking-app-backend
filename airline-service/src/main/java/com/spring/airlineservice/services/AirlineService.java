package com.spring.airlineservice.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.airlineservice.beans.Airline;
import com.spring.airlineservice.repository.AirlineRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AirlineService {
    
    private final AirlineRepository repo;

    public AirlineService(AirlineRepository repo) {
        this.repo = repo;
    }

    public Airline getAirline(String airlineName) {
        log.info("**********ENTERING GET AIRLINE BY NAME*********");
        Optional<Airline> optional = repo.findByAirlineName(airlineName);
        Airline airline = null;
        try {
            if (optional.isPresent()) {
                airline = optional.get();
            }else {
                log.warn("Airline '{}' can't be found!!!", airlineName);
            }
        }
        catch (Exception err) {
            log.error("An error occur : {}", err.getMessage());
        }
        log.info("**********LEAVING GET AIRLINE BY NAME SERVICE**********");
        return airline;
    }

    public List<Airline> getAirlines(int page, int pageSize) {
        log.info("**********ENTERING GET AIRLINES SERVICE**********");
        List<Airline> airlines = null;
        try {
            Pageable pageRequest = PageRequest.of(page, pageSize);
            airlines = repo.findAll(pageRequest).getContent();
        }
        catch (Exception err) {
            log.error("An error occur : {}", err.getMessage());
        }
        return airlines;
    }

    public Airline createOrUpdate(Airline airline) {
        log.info("**********ENTERING CREATE/UPDATE SERVICE**********");
        Airline rln = null;
        try {
            rln = repo.save(airline);
        } catch (Exception e) {
            log.error("Could not create or update : {}", e.getMessage());
        }
        return rln;
    }

    public void deleteByName(String airlineName) {
        log.info("**********ENTERING CREATE/UPDATE SERVICE**********");
        try {
            repo.deleteByAirlineName(airlineName);
        } catch (Exception e) {
            log.error("Could not delete airline '{}' : {}",airlineName, e.getMessage());
        }
    }

}