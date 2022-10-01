package com.spring.passengerdetailsservice.controller;

import com.spring.passengerdetailsservice.model.Passenger;
import com.spring.passengerdetailsservice.service.PassengerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/passengers")
public class PassengerController {
    private final PassengerService service;

    public PassengerController(PassengerService service) {
        this.service = service;
    }

    //get mapping endpoint to expose all passengers
    @GetMapping
    public List<Passenger> getAllPassengers(@RequestParam(defaultValue = "0") int page) {
        // try to get the list of passengers ang log the result
        try {
            log.info("Getting all passengers");
            return service.getAllPassengers(page);
        } catch (Exception e) {
            log.error("Error getting all passengers", e);
            throw e;
        }
    }

    //get mapping endpoint to expose passenger by passport number
    @GetMapping("/{passportNumber}")
    public Passenger getPassengerByPassportNumber(@PathVariable String passportNumber) {
        //try to retrieve a passenger and log the result
        try {
            Passenger passenger = service.getPassengerByPassportNumber(passportNumber);
            log.info("Passenger with passport number {} found", passportNumber);
            return passenger;
        } catch (Exception e) {
            log.error("Passenger with passport number {} not found", passportNumber);
            return null;
        }

    }

    //post mapping endpoint to expose save passenger
    @PostMapping
    public Passenger savePassenger(@RequestBody Passenger passenger) {
        // try to save a passenger and log the result
        try {
            log.info("Saving passenger: " + passenger);
            return service.savePassenger(passenger);
        } catch (Exception e) {
            log.error("Error saving passenger: " + e.getMessage());
            return null;
        }
    }

    //put mapping endpoint to expose update passenger
    @PutMapping
    public Passenger updatePassenger(@RequestBody Passenger passenger) {
        // try to update a passenger and log the result
        try {
            log.info("Updating passenger: " + passenger);
            return service.updatePassenger(passenger);
        } catch (Exception e) {
            log.error("Error updating passenger: " + e.getMessage());
            return null;
        }
    }

    //delete mapping endpoint to expose delete passenger
    @DeleteMapping("/{passportNumber}")
    public void deletePassenger(@PathVariable String passportNumber) {
        // try to delete passenger and log the result
        try {
            log.info("Deleting passenger with passport number: " + passportNumber);
            service.deletePassenger(service.getPassengerByPassportNumber(passportNumber));
        } catch (Exception e) {
            log.error("Error deleting passenger: " + e.getMessage());
        }
    }


}
