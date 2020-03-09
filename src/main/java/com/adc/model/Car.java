package com.adc.model;

import lombok.Data;

import java.util.Optional;

@Data
public class Car {
    private String brand;
    private Optional<Insurance> insurance;
}
