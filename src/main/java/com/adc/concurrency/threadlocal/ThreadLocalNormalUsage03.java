package com.adc.concurrency.threadlocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 每个线程持有一个DateFormatter，避免线程安全问题
 */
public class ThreadLocalNormalUsage03 {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executor.submit(() -> {
                System.out.println(toDate(finalI));
            });
        }
        executor.shutdown();
    }

    private static String toDate(int seconds) {
        DateFormat formatter = ThreadSafeFormatter2.holder.get();
        return formatter.format(new Date(seconds * 1000));
    }

    static class ThreadSafeFormatter1 {
        public static ThreadLocal<DateFormat> holder = new ThreadLocal<>(){
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
        };
    }

    static class ThreadSafeFormatter2 {
        // 推荐写法：简洁
        public static ThreadLocal<DateFormat> holder = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
}


