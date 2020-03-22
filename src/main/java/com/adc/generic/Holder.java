package com.adc.generic;

public class Holder<T> {

    private T obj;

    public Holder(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
