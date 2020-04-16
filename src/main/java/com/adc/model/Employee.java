package com.adc.model;

import java.io.Serializable;

public class Employee implements Serializable {
    private String name;
    private Integer salary;

    public Employee(String name, Integer salary) {
        this.name = name;
        this.salary = salary;
    }

    public void raiseSalary(Integer value) {
        salary = salary + value;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
