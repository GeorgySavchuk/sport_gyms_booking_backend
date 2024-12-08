package org.example.booking_service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.booking_service.repository.BookingRepository;
import com.example.booking_service.service.BookingService;
import com.example.booking_service.service.FacilityService;
import com.example.booking_service.service.UserService;
import com.example.common.model.Booking;
import com.example.common.model.DTO.BookingDTO;
import com.example.common.model.DTO.WeekIntervalDTO;
import com.example.common.model.SportFacility;
import com.example.common.model.User;

public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserService userService;

    @Mock
    private FacilityService facilityService;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBooking() {
        BookingDTO bookingDTO = new BookingDTO(1L, 1L, "01.01.2023 10:00", "01.01.2023 12:00");

        User user = new User();
        SportFacility sportFacility = new SportFacility();
        sportFacility.setBookingPrice(100);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(bookingDTO.getStartTime(), formatter);
        LocalDateTime endTime = LocalDateTime.parse(bookingDTO.getEndTime(), formatter);

        when(userService.getByUserId(1L)).thenReturn(Optional.of(user));
        when(facilityService.getSportFacilityById(1L)).thenReturn(Optional.of(sportFacility));
        when(bookingRepository.findByUserAndSportFacilityAndStartTimeAndEndTime(user, sportFacility, startTime, endTime)).thenReturn(Optional.empty());

        boolean result = bookingService.addBooking(bookingDTO);

        assertTrue(result);
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    public void testGetBookingsForUser() {
        User user = new User();
        LocalDateTime currentDateTime = LocalDateTime.now();
        when(userService.getByUserId(1L)).thenReturn(Optional.of(user));
        when(bookingRepository.findByUserAndCurrentDateTime(eq(user), any(LocalDateTime.class))).thenReturn(List.of(new Booking()));

        List<Booking> bookings = bookingService.getBookingsForUser(1L);

        assertFalse(bookings.isEmpty());
    }

    @Test
    public void testGetBookingScheduleForWeek() {
        SportFacility sportFacility = new SportFacility();
        sportFacility.setWorkInterval("10:00-18:00");
        when(facilityService.getSportFacilityById(1L)).thenReturn(Optional.of(sportFacility));
        when(bookingRepository.existsBySportFacilityAndStartTimeAndEndTime(any(), any(), any())).thenReturn(false);

        List<WeekIntervalDTO> schedule = bookingService.getBookingScheduleForWeek(1L, 0);

        assertFalse(schedule.isEmpty());
    }

    @Test
    public void testDeleteBooking() {
        Booking booking = new Booking();
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        boolean result = bookingService.deleteBooking(1L);

        assertTrue(result);
        verify(bookingRepository, times(1)).delete(booking);
    }
}