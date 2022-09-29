package com.spring.airlineservice.services;



import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.spring.airlineservice.beans.Airline;
import com.spring.airlineservice.repository.AirlineRepository;


@ExtendWith(MockitoExtension.class)
public class AirlineServiceTest {

	@Mock
	private AirlineRepository repo;

	@InjectMocks
	private AirlineService service;

	private Airline airline;

	@BeforeEach
	void setUp() {
		airline = Airline.builder()
			.airlineNo(UUID.randomUUID())
			.airlineName("Emirates")
			.iata("15A13")
				.build();
	}

	@Test
	void givenAirline_whenSave_thenReturnAirline() {
		given(repo.save(airline)).willReturn(airline);

		Airline savedAirline = service.createOrUpdate(airline);

		assertThat(savedAirline).isNotNull();
	}

	@Test
	void givenAirline_whenUpdate_thenReturnUpdatedAirline() {
		given(repo.save(airline)).willReturn(airline);

		airline.setAirlineName("TAP");

		Airline updated = service.createOrUpdate(airline);

		assertThat(updated.getAirlineName()).isEqualTo("TAP");
	}

	
	
}
