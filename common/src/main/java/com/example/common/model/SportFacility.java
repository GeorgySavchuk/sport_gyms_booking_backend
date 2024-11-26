package com.example.common.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "sport_facilities")
public class SportFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String metro;

    @Column(nullable = false)
    private String workInterval;

    @Column(nullable = false)
    private String areaParams;

    @Column(nullable = false)
    private String coatingType;

    @Column(nullable = false)
    private String areaType;

    @Column(nullable = false)
    private boolean changingRooms;

    @Column(nullable = false)
    private boolean tribunes;

    @Column(nullable = false)
    private boolean shower;

    @Column(nullable = false)
    private boolean lighting;

    @Column(nullable = false)
    private boolean parkingSpace;

    @Column(nullable = true)
    private String parkingType;

    @Column(nullable = false)
    private int bookingPrice;

    @Column(nullable = false)
    private int maxPeopleCount;

    @JsonIgnoreProperties("sportFacility")
    @OneToMany(mappedBy = "sportFacility")
    private List<Booking> bookings = new ArrayList<>();

    public SportFacility(String title, String image, String address, String metro, String workInterval, String areaParams, String coatingType, String areaType, boolean changingRooms, boolean tribunes, boolean shower, boolean lighting, boolean parkingSpace, String parkingType, int bookingPrice, int maxPeopleCount) {
        this.title = title;
        this.image = image;
        this.address = address;
        this.metro = metro;
        this.workInterval = workInterval;
        this.areaParams = areaParams;
        this.coatingType = coatingType;
        this.areaType = areaType;
        this.changingRooms = changingRooms;
        this.tribunes = tribunes;
        this.shower = shower;
        this.lighting = lighting;
        this.parkingSpace = parkingSpace;
        this.parkingType = parkingType;
        this.bookingPrice = bookingPrice;
        this.maxPeopleCount = maxPeopleCount;
    }
}
