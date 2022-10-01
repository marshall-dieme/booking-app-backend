package com.spring.flightservice.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Flight {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(unique = true, length = 10)
    private String flightNumber;

    private String origin;

    private String destination;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime departureDateTime;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime arrivalDateTime;

    private Double price;

    private Boolean status;

    private Integer availableSeats;

    private Integer totalSeats;

    private Integer bookedSeats;
}