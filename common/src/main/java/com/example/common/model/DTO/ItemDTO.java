package com.example.common.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ItemDTO {
    private String time;
    private boolean isBooked;
    private int price;
}
