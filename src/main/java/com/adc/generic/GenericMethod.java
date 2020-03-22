package com.adc.generic;

public class GenericMethod {

    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }

    public <A, B> void f(A a, B b, Integer c) {
        System.out.println(a.getClass().getName() + " " + b.getClass().getName() + " " + c.getClass().getName());
    }
}
