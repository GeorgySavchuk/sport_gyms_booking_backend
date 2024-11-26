package com.example.booking_service.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.common.model.Booking;
import com.example.common.model.SportFacility;
import com.example.common.model.User;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByUserAndSportFacilityAndStartTimeAndEndTime(
            User user,
            SportFacility sportFacility,
            LocalDateTime startTime,
            LocalDateTime endTime
    );

    @Query("SELECT b FROM Booking b WHERE b.user = :user AND b.startTime >= :currentDateTime")
    List<Booking> findByUserAndCurrentDateTime(@Param("user") User user, @Param("currentDateTime") LocalDateTime currentDateTime);

    @Query("SELECT COUNT(b) > 0 FROM Booking b WHERE b.sportFacility = :sportFacility AND b.startTime = :startTime AND b.endTime = :endTime")
    boolean existsBySportFacilityAndStartTimeAndEndTime(
            @Param("sportFacility") SportFacility sportFacility,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
};