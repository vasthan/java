package com.adc.generic;

import java.util.ArrayList;
import java.util.List;

public class GenericVarargs {

    @SafeVarargs
    public static <T> List<T> makeList(T... args) {
        List<T> result = new ArrayList<>();
        for (T arg : args) {
            result.add(arg);
        }
        return result;
    }

    public static void main(String[] args) {
        GenericVarargs varargs = new GenericVarargs();
        List<String> result = varargs.makeList("java go c python".split(" "));
        System.out.println(result);
    }
}
