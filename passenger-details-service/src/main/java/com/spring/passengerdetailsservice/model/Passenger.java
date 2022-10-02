package com.spring.passengerdetailsservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passenger {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(unique = true, nullable = false, length = 15)
    @NotNull
    @NotBlank
    @Size(min = 15, max = 15)
    private String passportNumber;

    @NotBlank
    @NotNull
    @NotEmpty
    private String firstName;

    @NotBlank
    @NotNull
    @NotEmpty
    private String lastName;

    @NotBlank
    @NotNull
    @NotEmpty
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @NotNull
    @NotEmpty
    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @NotBlank
    @NotNull
    @NotEmpty
    private String country;

    @NotBlank
    @NotNull
    @NotEmpty
    private String state;

    @NotBlank
    @NotNull
    @NotEmpty
    private String zipCode;

}
