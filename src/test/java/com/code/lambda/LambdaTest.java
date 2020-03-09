package com.code.lambda;

import com.adc.model.Apple;
import com.adc.util.AppleUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;

public class LambdaTest {
    private List<Apple> inventory;

    @Before
    public void before() {
        inventory = new ArrayList<>();
        inventory.add(new Apple("green", 12));
        inventory.add(new Apple("green", 400));
        inventory.add(new Apple("red", 20));
        inventory.add(new Apple("red", 500));
    }

    @After
    public void after() {

    }

    /**
     * java8之前的写法
     */
    @Test
    public void listHiddenFileTest1() {
        File home = new File("/users/yanghanqing");
        File[] files = home.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });
        System.out.printf("hidden file count: %d \n", files.length);
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }

    /**
     * java8写法
     */
    @Test
    public void listHiddenFileTest2() {
        File home = new File("/users/yanghanqing");
        File[] files = home.listFiles(File::isHidden);
        System.out.printf("hidden file count: %d \n", files.length);
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }

    /**
     * 传递方法作为参数，不是最好的做法
     * 最好lambda
     */
    @Test
    public void filterAppleTest1() {
        List<Apple> greenApples = filterApples(inventory, Apple::isGreenApple);
        List<Apple> weightApples = filterApples(inventory, Apple::isHeavyApple);
        System.out.println("greenApples: " + greenApples);
        System.out.println("weightApples: " + weightApples);
    }

    /**
     * lambda表达式，匿名函数
     * 不需要额外定义isGreenApple()或isHeavyApple()这些方法，因为可能只是临时用一次
     */
    @Test
    public void filterAppleTest2() {
        List<Apple> greenApples = filterApples(inventory, apple -> "green".equals(apple.getColor()));
        List<Apple> weightApples = filterApples(inventory, apple -> apple.getWeight() > 300);
        System.out.println("greenApples: " + greenApples);
        System.out.println("weightApples: " + weightApples);
    }

    private static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    @Test
    public void printApple() {
        AppleUtil.prettyPrintApple(inventory, apple -> apple.getColor() + " " + apple.getWeight());
    }

    @Test
    public void biFunctionTest() {

        BinaryOperator<Integer> function1 = (a, b) -> a + b;
        IntBinaryOperator function2 = (a, b) -> a + b;
    }
}
