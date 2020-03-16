package com.adc.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// 主线程等待多个子线程
public class CountDownLatchTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(2);

        CyclicBarrier barrier = new CyclicBarrier(2);

        List<Integer> resource1 = new ArrayList<>(); // 资源1
        List<Integer> resource2 = new ArrayList<>(); // 资源2

        executor.execute(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " 开始执行");
                Thread.sleep(60000);
                resource1.add(100);
                System.out.println(Thread.currentThread().getName() + " 执行结束");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " 开始执行");
                Thread.sleep(60000);
                resource2.add(200);
                System.out.println(Thread.currentThread().getName() + " 执行结束");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            // 主线程等待
            System.out.println(Thread.currentThread().getName() + " 等待中。。。");
            latch.await();
            System.out.println(Thread.currentThread().getName() + " 结束等待");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("resource1: " + resource1);
        System.out.println("resource2: " + resource2);

        // 关闭线程池
        executor.shutdown();
    }
}
