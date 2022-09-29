package com.spring.airlineservice;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.spring.airlineservice.beans.Airline;
import com.spring.airlineservice.repository.AirlineRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class AirlineServiceApplication implements CommandLineRunner {

	@Autowired
	AirlineRepository repo;
	public static void main(String[] args) {
		SpringApplication.run(AirlineServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Airline airline = new Airline();
		airline.setAirlineName("Emirates Airlines");
		airline.setAirlineNo(UUID.randomUUID());
		airline.setIata("153694");
		repo.save(airline);
		
	}

}
