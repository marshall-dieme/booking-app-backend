package com.spring.flightservice.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @Column(unique = true)
    private String flightNumber;

    @NotBlank
    @NotNull
    @NotEmpty
    private String origin;

    @NotBlank
    @NotNull
    @NotEmpty
    private String destination;

    @NotBlank
    @NotNull
    @NotEmpty
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime departureDateTime;

    @NotBlank
    @NotNull
    @NotEmpty
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime arrivalDateTime;

    @NotBlank
    @NotNull
    @NotEmpty
    private Double price;


    private Boolean status;

    @NotBlank
    @NotNull
    @NotEmpty
    private Integer availableSeats;

    @NotBlank
    @NotNull
    @NotEmpty
    private Integer totalSeats;

    private Integer bookedSeats;
}