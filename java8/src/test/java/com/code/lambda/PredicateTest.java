package com.code.lambda;

import com.feature.model.Apple;
import com.feature.util.AppleUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateTest {

    private List<Apple> apples;

    @Before
    public void before() {
        apples = AppleUtil.createApples(11);
    }

    @Test
    public void andTest() {
        Predicate<Apple> red = a -> "red".equals(a.getColor());
        List<Apple> redApples = filter(apples, red);
        System.out.println(redApples);

        Predicate<Apple> heavy = a -> a.getWeight() > 300;
        List<Apple> heavyApples = filter(apples, heavy);
        System.out.println(heavyApples);

        List<Apple> redAndHeavyApples = filter(apples, red.and(heavy));
        System.out.println(redAndHeavyApples);
    }

    @Test
    public void orTest() {
        Predicate<Apple> red = a -> "red".equals(a.getColor());
        Predicate<Apple> heavy = a -> a.getWeight() > 300;

        List<Apple> redOrHeavyApples = filter(apples, red.or(heavy));
        System.out.println(redOrHeavyApples);
    }

    @Test
    public void negateTest() {
        Predicate<Apple> red = a -> "red".equals(a.getColor());
        Predicate<Apple> heavy = a -> a.getWeight() > 100;

        List<Apple> notRedAndHeavyApples = filter(apples, red.or(heavy).negate());
        System.out.println(notRedAndHeavyApples);
    }


    private List<Apple> filter(List<Apple> apples, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();

        apples.forEach(a -> {
            if (p.test(a)) {
                result.add(a);
            }
        });
        return result;
    }
}
