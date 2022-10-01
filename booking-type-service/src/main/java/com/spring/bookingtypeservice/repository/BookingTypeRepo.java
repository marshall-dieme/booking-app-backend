package com.spring.bookingtypeservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.bookingtypeservice.model.BookingType;

@Repository
public interface BookingTypeRepo extends JpaRepository<BookingType, UUID> {

    BookingType findByName(String name);

}