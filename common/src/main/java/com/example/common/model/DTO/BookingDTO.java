package com.example.common.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BookingDTO {
    private Long userId;
    private Long sportFacilityId;
    private String startTime;
    private String endTime;
}
