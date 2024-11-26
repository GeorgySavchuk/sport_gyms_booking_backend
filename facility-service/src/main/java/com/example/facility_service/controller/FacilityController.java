package com.example.facility_service.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.model.DTO.SportFacilityDTO;
import com.example.common.model.SportFacility;
import com.example.facility_service.service.FacilityService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/facilities")
public class FacilityController {
    private final FacilityService facilityService;

    @PostMapping("/add")
    public String addSportFacility(@RequestBody SportFacilityDTO sportFacilityDTO) {
        if (facilityService.addFacility(sportFacilityDTO)) {
            return "sport facility added";
        } else {
            return "sport facility already exists";
        }
    }

    @GetMapping("/all")
    public List<SportFacility> showAllSportFacilities() {
        return facilityService.getAllSportFacilities();
    }

    @GetMapping("/{id}")
    public Optional<SportFacility> showSportFacilityById(@PathVariable int id) {
        return facilityService.getSportFacilityById((long) id);
    }
}
