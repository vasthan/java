package com.adc.generic;

import com.adc.generic.coffee.Mocha;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class BasicSupplier<T> implements Supplier<T> {

    private Class<T> type;

    private BasicSupplier(Class<T> type) {
        this.type = type;
    }

    @Override
    public T get() {
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Supplier<T> create(Class<T> type) {
        return new BasicSupplier<>(type);
    }

    public static void main(String[] args) {
        Stream.generate(BasicSupplier.create(Mocha.class))
                .limit(5)
                .map(e -> e + " ")
                .forEach(System.out::println);
    }
}
