package com.adc.generic;

// 二元组
public class TwoTuple<A, B> {
    public final A a;
    public final B b;

    public TwoTuple(A a, B b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "TwoTuple{" + "a=" + a + ", b=" + b + '}';
    }
}
