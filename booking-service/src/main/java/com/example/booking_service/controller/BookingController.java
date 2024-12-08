package com.example.booking_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/bookings")
@Tag(name = "BookingController", description = "Контроллер для бронирования спортивных площадок")
public class BookingController {
    private final BookingService bookingService;

    @Operation(summary = "Бронирование спортивной площадки", description = "Бронирует спортивную площадку")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Бронь прошла успешно"),
        @ApiResponse(responseCode = "400", description = "Неправильный ввод")
    })
    @PostMapping("/add")
    public String addBooking(@Parameter(description = "Booking details to be added", required = true) @RequestBody BookingDTO bookingDTO) {
        if (bookingService.addBooking(bookingDTO)) {
            return "Booking added";
        } else {
            return "Booking not added";
        }
    }

    @Operation(summary = "Получение списка бронирований пользователя", description = "Возвращает историю бронирований пользователя")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Список бронирований успешно получен"),
        @ApiResponse(responseCode = "404", description = "Такого пользователя нет")
    })
    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsForUser(@Parameter(description = "ID of the user whose bookings are to be retrieved", required = true) @PathVariable Long userId) {
        return bookingService.getBookingsForUser(userId);
    }

    @Operation(summary = "Получение расписание спортивной площадки на текущую неделю", description = "Возвращает расписание спортивной площадки на текущую неделю")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Расписание успешно пришло"),
        @ApiResponse(responseCode = "404", description = "Спортивной площадки не нашлось")
    })
    @GetMapping("/schedule/{sportFacilityId}")
    public List<WeekIntervalDTO> getBookingScheduleForWeek(
            @Parameter(description = "ID of the sport facility", required = true) @PathVariable Long sportFacilityId,
            @RequestParam(value = "weekIndex", defaultValue = "0", required = false) @Parameter(description = "Index of the week for which the schedule is requested") int weekIndex) {
        return bookingService.getBookingScheduleForWeek(sportFacilityId, weekIndex);
    }

    @Operation(summary = "Удаление бронирования", description = "Удаляет бронирование по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Бронь успешно удалена"),
        @ApiResponse(responseCode = "404", description = "Бронь не найдена")
    })
    @DeleteMapping("/remove/{bookingId}")
    public String deleteBooking(@Parameter(description = "ID бронирования", required = true) @PathVariable Long bookingId) {
        if (bookingService.deleteBooking(bookingId)) {
            return "Booking deleted";
        } else {
            return "Booking not found";
        }
    }
}