package com.spring.passengerdetailsservice.repository;

import com.spring.passengerdetailsservice.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, UUID> {

    Passenger findByFirstNameAndLastName(String firstName, String lastName);

    Passenger findByPassportNumber(String passportNumber);
}
