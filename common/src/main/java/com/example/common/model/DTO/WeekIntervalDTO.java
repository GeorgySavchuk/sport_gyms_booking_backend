package com.example.common.model.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class WeekIntervalDTO {
    private String date;
    private List<ItemDTO> items;
}
