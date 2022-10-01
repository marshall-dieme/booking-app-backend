package com.spring.passengerdetailsservice.service;

import com.spring.passengerdetailsservice.model.Passenger;
import com.spring.passengerdetailsservice.repository.PassengerRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    //get all passengers with pagination
    public List<Passenger> getAllPassengers(int page) {
        Pageable pageable = PageRequest.of(page, 20);
        return passengerRepository.findAll(pageable).getContent();
    }

    //get passenger by first name and last name
    public Passenger getPassengerByFirstNameAndLastName(String firstName, String lastName) {
        return passengerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    // get passenger by passport number
    public Passenger getPassengerByPassportNumber(String passportNumber) {
        return passengerRepository.findByPassportNumber(passportNumber);
    }

    // save passenger
    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    // update passenger
    public Passenger updatePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    // delete passenger
    public void deletePassenger(Passenger passenger) {
        passengerRepository.delete(passenger);
    }

    // get passenger id by passport number
    public UUID getPassengerIdByPassportNumber(String passportNumber) {
        return passengerRepository.findByPassportNumber(passportNumber).getId();
    }
}
