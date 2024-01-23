package com.myspring.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Device {
    private long id;
    private String name;
    private String details;
    private double price;
}
