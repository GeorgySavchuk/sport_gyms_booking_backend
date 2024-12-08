package com.example.facility_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.common.model"})
@EnableJpaRepositories(basePackages = {"com.example.facility_service.repository"})
public class FacilityServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(FacilityServiceApplication.class, args);
	}

}
