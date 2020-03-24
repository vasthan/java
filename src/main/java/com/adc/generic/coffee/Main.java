package com.adc.generic.coffee;

import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Stream.generate(new CoffeeSupplier())
                .limit(5)
                .forEach(System.out::println);
        System.out.println("----------------");
        for (Coffee c : new CoffeeSupplier(5)) {
            System.out.println(c);
        }
    }
}
