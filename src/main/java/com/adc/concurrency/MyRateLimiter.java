package com.adc.concurrency;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 限流器
 */
public class MyRateLimiter {

    public static void main(String[] args) {
        // guava限流器
        RateLimiter limiter = RateLimiter.create(2);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 20; i++) {
            long last = System.nanoTime();
            limiter.acquire();
            long cur = System.nanoTime();
            executor.submit(() -> {
                System.out.println((cur - last) / 1000000);
            });
        }
        executor.shutdown();
    }
}
