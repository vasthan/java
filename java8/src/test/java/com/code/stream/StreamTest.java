package com.code.stream;

import com.feature.model.Dish;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {

    private List<Dish> menu;

    @Before
    public void init() {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    /**
     * 中间操作不会消费流，而是建立一个流水线
     * 遇到终端操作，才会消费
     * 每个元素走一遍流水线
     */
    @Test
    public void test() {
        long count = menu.stream()
                .filter(x -> {
                    System.out.println(x);
                    return x.getCalories() >= 400;
                })
                .filter(x -> {
                    System.out.println(x);
                    return x.isVegetarian();
                })
                .map(x -> {
                    System.out.println(x);
                    return x.getName();
                })
                .count();
        System.out.println(count);

    }

    @Test
    public void arrStream() {
        int[] arr = new int[]{1,2,3};
        Arrays.stream(arr).forEach(System.out::println);
    }

    @Test
    public void distinctTest() {
        List<Integer> nums = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10,2,8,7,12,3,0);
        nums.stream().filter(x -> x % 2 == 0).distinct().forEach(System.out::println);
    }

    @Test
    public void limitTest() {
        menu.stream().filter(x -> x.getCalories() > 400).limit(3).forEach(System.out::println);
    }

    @Test
    public void skipTest() {
        menu.stream().filter(x -> !x.isVegetarian()).limit(2).forEach(System.out::println);
    }

    @Test
    public void mapTest() {
        List<Integer> num = Arrays.asList(1, 2, 3, 4, 5);
        num.stream()
                .map(n -> n * n)
                .forEach(System.out::println);
    }

    /**
     * 返回数对
     */
    @Test
    public void flatMapTest() {
        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(4, 5);

        num1.stream()
                .flatMap(i ->
                        num2.stream()
                                .filter(j -> (i + j) % 3 == 0)
                                .map(j -> new int[]{i, j}))
                .forEach(p -> System.out.println(Arrays.toString(p)));
    }

    @Test
    public void findAnyTest() {
        Optional<Dish> dish = menu.stream().findAny();
        System.out.println(dish.get());

        dish = menu.stream().filter(x -> x.isVegetarian()).findAny();
        System.out.println(dish.get());
    }

    @Test
    public void anyMatchTest() {
        boolean b = menu.stream().anyMatch(x -> x.getCalories() == 1200);
        System.out.println(b);
    }

    @Test
    public void reduceTest() {
        List<Integer> num = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum = num.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        Optional<Integer> min = num.stream().min(Integer::compareTo);
        System.out.println(min.get());
    }

    @Test
    public void rangeTest() {
        int a = 4;
        Stream<int[]> stream = IntStream.rangeClosed(1, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .boxed()
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});
    }

    /**
     * 1-100之间的勾股数
     */
    @Test
    public void range2Test() {
        Stream<Stream<int[]>> streamStream = IntStream.rangeClosed(1, 100).boxed()
                .map(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );

        // 流扁平化
        Stream<int[]> flatStream = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );

        // 上面的平方根计算流两次，可以优化
        // 先生成所有的三元数，在筛选符合条件的
        Stream<double[]> stream = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0)
                )
                .limit(5);

        stream.forEach(dArr -> System.out.println(Arrays.toString(dArr)));
    }

    @Test
    public void genStreamTest() throws IOException {
        // 值序列生成流
        Stream<String> fruitStream = Stream.of("apple", "orange", "banana");
        fruitStream.forEach(System.out::println);

        // 数组生成流
        int[] scores = new int[]{90,81,100,72};
        IntStream scoreStream = Arrays.stream(scores);
        scoreStream.forEach(System.out::println);

        // 文件生成流
        Stream<String> lineStream = Files.lines(Paths.get("/Users/yanghanqing/code/java/java8/src/test/resources/data.txt"), Charset.defaultCharset());
        lineStream.forEach(System.out::println);

    }

    @Test
    public void infiniteStream() {
        // 无限流 如果不加limit会无限计算下去
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

        // 斐波那契元组
        Stream<int[]> tupleStream = Stream.iterate(new int[]{0, 1}, tuple -> new int[]{tuple[1], tuple[0] + tuple[1]}).limit(10);
        tupleStream.forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        // 斐波那契数列
        IntStream fib = IntStream.generate(new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        });
        fib.limit(10).forEach(System.out::println);

    }
}
