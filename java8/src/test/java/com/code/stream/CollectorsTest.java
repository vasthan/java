package com.code.stream;

import com.alibaba.fastjson.JSON;
import com.feature.model.Apple;
import com.feature.model.Dish;
import com.feature.util.AppleUtil;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * 收集器
 */
public class CollectorsTest {

    private List<Apple> apples;

    @Before
    public void init() {
        apples = AppleUtil.createApples(16);
    }

    @Test
    public void toListTest() {
        List<Apple> list = apples.stream().filter(apple -> "red".equals(apple.getColor())).collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * 计数
     */
    @Test
    public void countingTest() {
        // 方法1
        Long a = apples.stream().filter(apple -> "red".equals(apple.getColor())).collect(Collectors.counting());
        System.out.println(a);

        // 方法2
        long b = apples.stream().filter(apple -> "red".equals(apple.getColor())).count();
        System.out.println(b);
    }

    /**
     * 最大值/最小值
     */
    @Test
    public void maxMinTest() {
        Optional<Apple> maxWeightApple = apples.stream().collect(Collectors.maxBy(Comparator.comparingInt(Apple::getWeight)));
        if (maxWeightApple.isPresent()) {
            System.out.println(maxWeightApple);
        }

        Optional<Apple> minWeightApple = apples.stream().collect(Collectors.minBy(Comparator.comparingInt(Apple::getWeight)));
        if (minWeightApple.isPresent()) {
            System.out.println(minWeightApple);
        }
    }

    /**
     * 平均值
     */
    @Test
    public void averageTest() {
        Double averageWeight = apples.stream().collect(averagingInt(Apple::getWeight));
        System.out.println(averageWeight);
    }

    /**
     * 求和
     */
    @Test
    public void sumTest() {
        Integer totalWeight = apples.stream().collect(Collectors.summingInt(Apple::getWeight));
        System.out.println(totalWeight);
    }

    /**
     * 获取汇总信息对象
     */
    @Test
    public void summarizingTest() {
        IntSummaryStatistics statistics = apples.stream().collect(Collectors.summarizingInt(Apple::getWeight));
        System.out.println(statistics);
    }

    @Test
    public void joiningTest() {
        List<Dish> menu = Lists.newArrayList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("rice", true, 350, Dish.Type.OTHER));

        String names = menu.stream().map(dish -> dish.getName()).collect(Collectors.joining(","));
        System.out.println(names);
    }

    @Test
    public void reducingTest() {

        // reducing 求和
        Integer totalWeight = apples.stream().collect(Collectors.reducing(0, Apple::getWeight, (a, b) -> a + b));
        System.out.println(totalWeight);

        // reducing 求最大值
        Optional<Apple> maxWeightApple = apples.stream().collect(Collectors.reducing((a, b) -> a.getWeight() > b.getWeight() ? a : b));
        System.out.println(maxWeightApple.get());

        int sum = apples.stream().mapToInt(Apple::getWeight).sum();
        System.out.println(sum);

    }

    /**
     * 分组
     *
     * groupingBy方法的参数是一个分类函数
     *
     * 分类函数返回的值作为分组的键
     */
    @Test
    public void groupingByTest() {

        Map<String, List<Apple>> colorGroup = apples.stream().collect(Collectors.groupingBy(Apple::getColor));
        System.out.println(JSON.toJSONString(colorGroup));

        Map<String, List<Apple>> weightMap = apples.stream().collect(Collectors.groupingBy(a -> {
            if (a.getWeight() < 100) {
                return "light";
            } else if (a.getWeight() >= 100 && a.getWeight() < 300) {
                return "medium";
            } else {
                return "heavy";
            }
        }));
        System.out.println(JSON.toJSONString(weightMap));
    }

    /**
     * 多级分组
     */
    @Test
    public void multiGroupingByTest() {
        // 一级分类函数
        Function<Apple, String> funcGetColor = Apple::getColor;

        // 二级分类函数
        Function<Apple, String> funcGetWeight = a -> {
            if (a.getWeight() < 100) {
                return "light";
            } else if (a.getWeight() >= 100 && a.getWeight() < 300) {
                return "medium";
            } else {
                return "heavy";
            }
        };

        // 根据颜色和重量分组
        Map<String, Map<String, List<Apple>>> colorWeightMap = apples.stream().collect(Collectors.groupingBy(funcGetColor, Collectors.groupingBy(funcGetWeight)));
        System.out.println(JSON.toJSONString(colorWeightMap));

        List<Apple> greenHeavyApple = colorWeightMap.getOrDefault("green", Maps.newHashMap()).getOrDefault("heavy", Lists.newArrayList());
        System.out.println(JSON.toJSONString(greenHeavyApple));

    }
}
