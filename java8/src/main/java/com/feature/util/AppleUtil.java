package com.feature.util;

import com.feature.model.Apple;
import com.feature.predicate.AppleFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppleUtil {

    public static void prettyPrintApple(List<Apple> apples, AppleFormatter<Apple> formatter) {
        if (apples == null || apples.isEmpty()) {
            return;
        }
        for (Apple apple : apples) {
            System.out.println(formatter.accept(apple));
        }
    }

    public static List<Apple> createApples(int size) {
        List<Apple> apples = new ArrayList<>();
        String[] color = {"red", "green", "yellow"};
        Random random = new Random();
        for (int i = 0;i < size;i++) {
            apples.add(new Apple(color[i % 3], random.nextInt(500)));
        }
        return apples;
    }
}
