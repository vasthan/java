package com.adc.generic;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 泛型和多态可以搭配使用
        Holder<List> holder = new Holder<>(new ArrayList());

        // 泛型二元组容器
        TwoTuple<String, Integer> tuple = new TwoTuple<>("Hello", 23);
        System.out.println(tuple);
        System.out.println(tuple.a);
        System.out.println(tuple.b);


        // 泛型方法
        GenericMethod method = new GenericMethod();
        method.f("1");
        method.f(1);
        method.f(1.2);
        method.f(method);

        method.f(new TwoTuple<>(1, 2), new ThreeTuple<>(1, 2, 3), 1);

    }
}
