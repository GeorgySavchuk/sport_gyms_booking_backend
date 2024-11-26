package com.example.booking_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.common.model.SportFacility;

public interface FacilityRepository extends JpaRepository<SportFacility, Long> {
    Optional<SportFacility> findByAddressAndTitle(String address, String title);
}