package com.example.facility_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.model.DTO.SportFacilityDTO;
import com.example.common.model.SportFacility;
import com.example.facility_service.service.FacilityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/facilities")
@Tag(name = "FacilityController", description = "Контроллер для управления спортивными объектами")
public class FacilityController {
    private final FacilityService facilityService;

    @Operation(summary = "Добавление спортивного объекта", description = "Добавляет новый спортивный объект")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Спортивный объект успешно добавлен"),
        @ApiResponse(responseCode = "400", description = "Спортивный объект уже существует")
    })
    @PostMapping("/add")
    public String addSportFacility(@Parameter(description = "Данные спортивного объекта для добавления", required = true) @RequestBody SportFacilityDTO sportFacilityDTO) {
        if (facilityService.addFacility(sportFacilityDTO)) {
            return "sport facility added";
        } else {
            return "sport facility already exists";
        }
    }

    @Operation(summary = "Получение списка всех спортивных объектов", description = "Возвращает список всех спортивных объектов")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Список спортивных объектов успешно получен")
    })
    @GetMapping("/all")
    public List<SportFacility> showAllSportFacilities() {
        return facilityService.getAllSportFacilities();
    }

    @Operation(summary = "Получение спортивного объекта по ID", description = "Возвращает спортивный объект по указанному ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Спортивный объект успешно получен"),
        @ApiResponse(responseCode = "404", description = "Спортивный объект не найден")
    })
    @GetMapping("/{id}")
    public Optional<SportFacility> showSportFacilityById(@Parameter(description = "ID спортивного объекта", required = true) @PathVariable int id) {
        return facilityService.getSportFacilityById((long) id);
    }

    @Operation(summary = "Удаление спортивного объекта", description = "Удаляет спортивный объект по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Спортивный объект успешно удален"),
        @ApiResponse(responseCode = "404", description = "Спортивный объект не найден")
    })
    @DeleteMapping("/remove/{id}")
    public String deleteSportFacility(@Parameter(description = "ID спортивного объекта", required = true) @PathVariable int id) {
        if (facilityService.deleteFacility((long) id)) {
            return "Sport facility deleted";
        } else {
            return "Sport facility not found";
        }
    }
}