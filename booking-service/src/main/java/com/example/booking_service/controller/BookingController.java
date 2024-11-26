package com.example.booking_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking_service.service.BookingService;
import com.example.common.model.Booking;
import com.example.common.model.DTO.BookingDTO;
import com.example.common.model.DTO.WeekIntervalDTO;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    
    @PostMapping("/add")
    public String addBooking(@RequestBody BookingDTO bookingDTO) {
        if (bookingService.addBooking(bookingDTO)) {
            return "Booking added";
        } else {
            return "Booking not added";
        }
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsForUser(@PathVariable Long userId) {
        return bookingService.getBookingsForUser(userId);
    }

    @GetMapping("/schedule/{sportFacilityId}")
    public List<WeekIntervalDTO> getBookingScheduleForWeek(
            @PathVariable Long sportFacilityId,
            @RequestParam(defaultValue = "0") int weekIndex) {
        return bookingService.getBookingScheduleForWeek(sportFacilityId, weekIndex);
    }
}
