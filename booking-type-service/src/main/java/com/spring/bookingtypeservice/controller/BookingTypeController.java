package com.spring.bookingtypeservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bookingtypeservice.model.BookingType;
import com.spring.bookingtypeservice.service.BookingTypeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/booking-type")
@Slf4j
public class BookingTypeController {
    private final BookingTypeService service;

    public BookingTypeController(BookingTypeService service) {
        this.service = service;
    }

    //endpoint to retrieve all booking types in a List
    @GetMapping
    public List<BookingType> getAllBookingTypes() {
        //log a message and return the List of booking types
        log.info("Retrieving all booking types");
        return service.getAllBookingTypes();
    }

    //endpoint to get UUID of a booking type by name
    @GetMapping("/{name}")
    public UUID getBookingTypeIdByName(@PathVariable String name) {
        //log a message and return the UUID of the booking type
        log.info("Retrieving booking type id by name");
        return service.getBookingTypeIdByName(name);
    }

    //endpoint to get name of a booking type by UUID
    @GetMapping("/id/{id}")
    public String getBookingTypeNameById(@PathVariable UUID id) {
        //log a message and return the name of the booking type
        log.info("Retrieving booking type name by id");
        return service.getBookingTypeNameById(id);
    }

    //endpoint to add a new booking type
    @PostMapping
    public BookingType addBookingType(@RequestBody BookingType bookingType) {
        //log a message and return the new booking type
        log.info("Adding new booking type");
        return service.addBookingType(bookingType);
    }

    //endpoint to update a booking type
    @PutMapping
    public BookingType updateBookingType(@RequestBody BookingType bookingType) {
        //log a message and return the updated booking type
        log.info("Updating booking type");
        return service.updateBookingType(bookingType);
    }

    //endpoint to delete a booking type by name
    @DeleteMapping("/{name}")
    public void deleteBookingTypeByName(@PathVariable String name) {
        //log a message and delete the booking type
        log.info("Deleting booking type by name");
        service.deleteBookingTypeByName(name);
    }

}