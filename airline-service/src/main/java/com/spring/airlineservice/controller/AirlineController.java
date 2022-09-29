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

    //@PreAuthorize("hasAuthority('SCOPE_TEST')")
    @GetMapping
    public List<Airline> getAirlines(@RequestParam(required = false, defaultValue = "0") int page, 
                                    @RequestParam(required = false, defaultValue = "10") int pageSize) {
        // SecurityContext context = SecurityContextHolder.getContext();
        // Authentication authentication = context.getAuthentication();
        // log.warn("Scopes : " + authentication.getAuthorities());
        return service.getAirlines(page, pageSize);
    }
    
    @GetMapping("/{airlineName}")
    public Airline getAirline(@PathVariable String airlineName) {
        return service.getAirline(airlineName);
    }

    @PostMapping
    public Airline createAirline(@RequestBody Airline airline) {   
        return service.createOrUpdate(airline);
    }
    
    @PutMapping
    public Airline editAirline(@RequestBody Airline airline) {
        return service.createOrUpdate(airline);
    }
    
    @DeleteMapping("/{airlineName}")
    public void deleteAirline(@PathVariable String airlineName) {
        service.deleteByName(airlineName);
    }
}
