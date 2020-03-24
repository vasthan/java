package com.adc.generic.coffee;

import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;

public class CoffeeSupplier implements Supplier<Coffee>, Iterable<Coffee> {

    private static Random rand = new Random(47);
    private Class<?>[] types = new Class[]{Latte.class, Mocha.class, Cappuccino.class, Americano.class, Breve.class};

    private int size;

    public CoffeeSupplier() {}

    public CoffeeSupplier(int size) {
        this.size = size;
    }

    @Override
    public Coffee get() {
        try {
            return (Coffee) types[rand.nextInt(types.length)].getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    class CoffeeIterator implements Iterator<Coffee> {
        int count = size;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public Coffee next() {
            count--;
            return CoffeeSupplier.this.get();
        }
    }
}
