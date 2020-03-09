package com.adc.model;

import lombok.Data;

import java.util.Optional;

@Data
public class Person {
    private String name;
    private int age;
    private Optional<Car> car;
}
