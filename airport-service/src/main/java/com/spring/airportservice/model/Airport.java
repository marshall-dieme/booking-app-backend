package com.spring.airportservice.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airport {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank
    @NotNull
    @NotEmpty
    private String name;

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(max = 10)
    @Column(unique = true, length = 10)
    private String iata;

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(max = 10)
    @Column(unique = true, length = 10)
    private String icao;

    @NotBlank
    @NotNull
    @NotEmpty
    private String city;

    @NotBlank
    @NotNull
    @NotEmpty
    private String country;

    @NotBlank
    @NotNull
    @NotEmpty
    private String timezone;

    @NotBlank
    @NotNull
    @NotEmpty
    private String latitude;

    @NotBlank
    @NotNull
    @NotEmpty
    private String longitude;

}