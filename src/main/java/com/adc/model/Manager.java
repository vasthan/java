package com.adc.model;

public class Manager extends Employee {
    private Employee secretary;

    public Manager(String name, Integer salary) {
        super(name, salary);
    }

    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "secretary=" + secretary +
                "} " + super.toString();
    }
}
