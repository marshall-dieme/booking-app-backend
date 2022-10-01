package com.spring.bookingtypeservice.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.spring.bookingtypeservice.model.BookingType;
import com.spring.bookingtypeservice.repository.BookingTypeRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class BookingTypeService {
    private final BookingTypeRepo repo;

    public BookingTypeService(BookingTypeRepo repo) {
        this.repo = repo;
    }

    //retrieve all booking types in a List
    public List<BookingType> getAllBookingTypes() {
        return repo.findAll();
    }

    //get UUID of a booking type by name
    public UUID getBookingTypeIdByName(String name) {
        //try to find the booking type by name in the database and return its UUID if found or log an error if not
        try {
            return repo.findByName(name).getId();
        } catch (NullPointerException e) {
            log.error("Booking type with name " + name + " not found");
            return null;
        }
    }
    
    //get name of a booking type by UUID
    public String getBookingTypeNameById(UUID id) {
        //try to find the booking type by UUID in the database and return its name if found or log an error if not
        try {
            return repo.findById(id).get().getName();
        } catch (NullPointerException e) {
            log.error("Booking type with id " + id + " not found");
            return null;
        }
    }

    //add a new booking type
    public BookingType addBookingType(BookingType bookingType) {
        //check if the booking type already exists in the database
        if (repo.findByName(bookingType.getName()) == null) {
            //if not, add it and return it
            return repo.save(bookingType);
        } else {
            //if it does, log an error and return null
            log.error("Booking type with name " + bookingType.getName() + " already exists");
            return null;
        }
    }

    //delete a booking type by name
    public void deleteBookingTypeByName(String name) {
        //try to find the booking type by name in the database and delete it if found or log an error if not
        try {
            repo.delete(repo.findByName(name));
        } catch (NullPointerException e) {
            log.error("Booking type with name " + name + " not found");
        }
    }

    //update a booking type
    public BookingType updateBookingType(BookingType bookingType) {
        //check if the booking type exists in the database
        if (repo.findById(bookingType.getId()).isPresent()) {
            //if it does, update it and return it
            return repo.save(bookingType);
        } else {
            //if it doesn't, log an error and return null
            log.error("Booking type with id " + bookingType.getId() + " not found");
            return null;
        }
    }



}