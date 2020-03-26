package com.adc.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// 批量获取价格
public class ConcurrentQueryPriceDemo1 {

    public List<Integer> batchPrices() throws ExecutionException, InterruptedException {

        // 1创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Future<Integer> price1 = executor.submit(this::getPrice1);
        Future<Integer> price2 = executor.submit(this::getPrice2);
        Future<Integer> price3 = executor.submit(this::getPrice3);

        long start = System.currentTimeMillis();
        List<Integer> prices = new ArrayList<>();

        // future.get()会阻塞主线程，如果比较慢，后续操作必须等待
        Integer p3 = price3.get();
        System.out.println("查询到价格 " + p3);

        Integer p2 = price2.get();
        System.out.println("查询到价格 " + p2);

        Integer p1 = price1.get();
        System.out.println("查询到价格 " + p1);

        prices.add(p1);
        prices.add(p2);
        prices.add(p3);
        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - start) / 1000.0 + "s");

        executor.shutdown();
        return prices;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> result1 = new ConcurrentQueryPriceDemo1().batchPrices();
        System.out.println(result1);
    }

    private Integer getPrice1() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }
    private Integer getPrice2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2;
    }

    private Integer getPrice3() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 3;
    }
}
