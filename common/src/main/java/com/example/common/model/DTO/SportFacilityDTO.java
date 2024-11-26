package com.example.common.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SportFacilityDTO {
    private String title;
    private String image;
    private String address;
    private String metro;
    private String workInterval;
    private String areaParams;
    private String coatingType;
    private String areaType;
    private boolean changingRooms;
    private boolean tribunes;
    private boolean shower;
    private boolean lighting;
    private boolean parkingSpace;
    private String parkingType;
    private int bookingPrice;
    private int maxPeopleCount;
}
