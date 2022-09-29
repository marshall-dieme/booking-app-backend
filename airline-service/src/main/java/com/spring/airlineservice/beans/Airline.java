package com.spring.airlineservice.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Airline {
    @Id
    @GeneratedValue
    private UUID airlineNo;

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 10)
    @Column(unique = true, length = 100)
    private String airlineName;

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(max = 10)
    @Column(unique = true, length = 10)
    private String iata;
}