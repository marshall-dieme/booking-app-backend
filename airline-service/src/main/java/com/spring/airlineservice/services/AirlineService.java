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

    public List<Airline> getAirlines(int page) {
        log.info("**********ENTERING GET AIRLINES SERVICE**********");
        List<Airline> airlines = null;
        try {
            Pageable pageRequest = PageRequest.of(page, 10);
            airlines = repo.findAll(pageRequest).getContent();
        }
        catch (Exception err) {
            log.error("An error occur : {}", err.getMessage());
        }
        return airlines;
    }

    //create a new airline
    public Airline createAirline(Airline airline) {
        log.info("**********ENTERING CREATE AIRLINE SERVICE**********");
        Airline newAirline = null;
        try {
            newAirline = repo.save(airline);
        } catch (Exception err) {
            log.error("An error occur : {}", err.getMessage());
        }
        log.info("**********LEAVING CREATE AIRLINE SERVICE**********");
        return newAirline;
    }
    
    //update an airline
    public Airline updateAirline(Airline airline) {
        log.info("**********ENTERING UPDATE AIRLINE SERVICE**********");
        Airline updatedAirline = null;
        try {
            updatedAirline = repo.save(airline);
        } catch (Exception err) {
            log.error("An error occur : {}", err.getMessage());
        }
        log.info("**********LEAVING UPDATE AIRLINE SERVICE**********");
        return updatedAirline;
    }

    //delete airline by name
    public void deleteAirline(String airlineName) {
        log.info("**********ENTERING DELETE AIRLINE SERVICE**********");
        try {
            repo.deleteByAirlineName(airlineName);
        } catch (Exception err) {
            log.error("An error occur : {}", err.getMessage());
        }
        log.info("**********LEAVING DELETE AIRLINE SERVICE**********");
    }

}