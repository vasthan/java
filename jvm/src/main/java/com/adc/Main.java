package com.adc;

/**
 * 分析字节码
 */
public class Main {
    private int m;

    private String[] names;

    public Main() {}

    public Main(int m) {
        this.m = m;
    }

    public int increment() {
        return m++;
    }

    public int incrementBy(int inc) {
        m += inc;
        return m;
    }

    public int test() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }
}
