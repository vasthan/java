package com.adc.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用{@link CompletionService}实现批量执行异步任务
 */
public class ConcurrentQueryPriceDemo2 {

    public List<Integer> batchPrices() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        cs.submit(this::getPrice3);
        cs.submit(this::getPrice2);
        cs.submit(this::getPrice1);

        long start = System.currentTimeMillis();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Integer price = cs.take().get();
            System.out.println("查询到价格 " + price);
            result.add(price);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - start) / 1000.0 + "s");

        executor.shutdown();
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> result = new ConcurrentQueryPriceDemo2().batchPrices();
        System.out.println(result);
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
