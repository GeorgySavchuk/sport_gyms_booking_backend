package com.example.booking_service.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.booking_service.repository.BookingRepository;
import com.example.common.model.Booking;
import com.example.common.model.DTO.BookingDTO;
import com.example.common.model.DTO.ItemDTO;
import com.example.common.model.DTO.WeekIntervalDTO;
import com.example.common.model.SportFacility;
import com.example.common.model.User;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class BookingService {
    BookingRepository bookingRepository;
    private final UserService userService;
    private final FacilityService sportFacilityService;

    public boolean addBooking(BookingDTO bookingDTO) {
        Optional<User> user = userService.getByUserId(bookingDTO.getUserId());
        Optional<SportFacility> sportFacility = sportFacilityService.getSportFacilityById(bookingDTO.getSportFacilityId());

        if (user.isPresent() && sportFacility.isPresent()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            LocalDateTime startTime = LocalDateTime.parse(bookingDTO.getStartTime(), formatter);
            LocalDateTime endTime = LocalDateTime.parse(bookingDTO.getEndTime(), formatter);

            if (bookingRepository.findByUserAndSportFacilityAndStartTimeAndEndTime(
                    user.get(),
                    sportFacility.get(),
                    startTime,
                    endTime
            ).isEmpty()) {
                Booking booking = new Booking(user.get(), sportFacility.get(), startTime, endTime, sportFacility.get().getBookingPrice());
                bookingRepository.save(booking);
                log.info("Booking added: {}", bookingDTO);
                return true;
            } else {
                log.info("Booking already exists: {}", bookingDTO);
                return false;
            }
        } else {
            log.info("User or Sport Facility not found: {}", bookingDTO);
            return false;
        }
    }

    public List<Booking> getBookingsForUser(Long userId) {
        Optional<User> user = userService.getByUserId(userId);
        if (user.isPresent()) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            return bookingRepository.findByUserAndCurrentDateTime(user.get(), currentDateTime);
        } else {
            log.info("User not found with ID: {}", userId);
            return List.of();
        }
    }

    public List<WeekIntervalDTO> getBookingScheduleForWeek(Long sportFacilityId, int weekIndex) {
        Optional<SportFacility> sportFacility = sportFacilityService.getSportFacilityById(sportFacilityId);
        if (sportFacility.isEmpty()) {
            log.info("Sport Facility not found with ID: {}", sportFacilityId);
            return List.of();
        }

        List<WeekIntervalDTO> weekIntervals = new ArrayList<>();
        LocalDate today = LocalDate.now().plusWeeks(weekIndex);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        String workInterval = sportFacility.get().getWorkInterval();
        String[] times = workInterval.split("-");
        LocalTime startTime = LocalTime.parse(times[0]);
        LocalTime endTime = LocalTime.parse(times[1]).minusHours(1);

        for (int i = 0; i < 7; i++) {
            LocalDate date = today.plusDays(i);
            String formattedDate = date.format(formatter);

            List<ItemDTO> items = new ArrayList<>();
            for (LocalTime time = startTime; !time.isAfter(endTime); time = time.plusHours(1)) {
                LocalDateTime startTimeOfDay = LocalDateTime.of(date, time);
                LocalDateTime endTimeOfDay = startTimeOfDay.plusHours(1);

                boolean isBooked = bookingRepository.existsBySportFacilityAndStartTimeAndEndTime(
                        sportFacility.get(),
                        startTimeOfDay,
                        endTimeOfDay
                );

                items.add(new ItemDTO(time.toString(), isBooked, sportFacility.get().getBookingPrice()));
            }

            weekIntervals.add(new WeekIntervalDTO(formattedDate, items));
        }

        return weekIntervals;
    }

    public boolean deleteBooking(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()) {
            bookingRepository.delete(booking.get());
            log.info("Booking deleted with ID: {}", bookingId);
            return true;
        } else {
            log.info("Booking not found with ID: {}", bookingId);
            return false;
        }
    }
}