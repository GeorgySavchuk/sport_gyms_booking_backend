package com.example.booking_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.booking_service.repository.FacilityRepository;
import com.example.common.model.DTO.SportFacilityDTO;
import com.example.common.model.SportFacility;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class FacilityService {
    private final FacilityRepository facilityRepositiory;

    public boolean addFacility(SportFacilityDTO facilityDTO) {
        if (facilityRepositiory.findByAddressAndTitle(facilityDTO.getAddress(), facilityDTO.getTitle()).isEmpty()) {
            SportFacility facility = new SportFacility(
                facilityDTO.getTitle(),
                facilityDTO.getImage(),
                facilityDTO.getAddress(),
                facilityDTO.getMetro(),
                facilityDTO.getWorkInterval(),
                facilityDTO.getAreaParams(),
                facilityDTO.getCoatingType(),
                facilityDTO.getAreaType(),
                facilityDTO.isChangingRooms(),
                facilityDTO.isTribunes(),
                facilityDTO.isShower(),
                facilityDTO.isLighting(),
                facilityDTO.isParkingSpace(),
                facilityDTO.getParkingType(),
                facilityDTO.getBookingPrice(),
                facilityDTO.getMaxPeopleCount()
            );

            facilityRepositiory.save(facility);
            log.info("Sport facility added: {}", facilityDTO);
            return true;
        }
        log.info("Sport facility already exists: {}", facilityDTO);
        return false;
    }

    public List<SportFacility> getAllSportFacilities() {
        log.info("Getting all sport facilities");
        return facilityRepositiory.findAll();
    }

    public Optional<SportFacility> getSportFacilityById(Long id) {
        log.info("Getting sport facility with id {}", id);
        return facilityRepositiory.findById(id);
    }
}