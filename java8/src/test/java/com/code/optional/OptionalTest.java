package com.code.optional;

import com.adc.model.Car;
import com.adc.model.Insurance;
import com.adc.model.Person;
import org.junit.Test;

import java.util.Optional;

/**
 * 可以把Optional看作是最多包含一个元素的Stream
 */
public class OptionalTest {

    @Test
    public void testMap() {
        Person person = new Person();
        person.setAge(18);
        person.setName("Martin");

        Car car = new Car();
        car.setBrand("MB");

        Insurance insurance = new Insurance();
        insurance.setName("RS");

        car.setInsurance(Optional.ofNullable(insurance));
        person.setCar(Optional.ofNullable(car));

        System.out.println(getCarInsuranceName(Optional.ofNullable(person)));
        System.out.println(getCarInsuranceName(Optional.ofNullable(person), 16));
        System.out.println(getCarInsuranceName(Optional.ofNullable(person), 20));
    }

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("unknown");
    }

    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() >= minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("unknown");
    }
}
