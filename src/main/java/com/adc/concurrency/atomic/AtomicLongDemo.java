package com.adc.concurrency.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongDemo {
    private static AtomicLong counter = new AtomicLong();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(20);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            executor.submit(new Task());
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("AtomicLong 计数：" + counter.get());
        System.out.println("AtomicLong 耗时：" + (System.currentTimeMillis() - start));
    }

    public static class Task implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.getAndIncrement();
            }
        }
    }
}
