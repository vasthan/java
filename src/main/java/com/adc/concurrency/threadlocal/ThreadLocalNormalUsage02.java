package com.adc.concurrency.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * - 多线程环境下，SimpleDateFormat线程不安全
 * - 对SimpleDateFormat代码临界区加锁
 */
public class ThreadLocalNormalUsage02 {

    static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(() -> System.out.println(date(finalI)));
        }
        threadPool.shutdown();
    }

    private static String date(int seconds) {
        Date date = new Date(seconds * 1000);
        String s = null;
        synchronized (ThreadLocalNormalUsage02.class) {
            s = dateFormat.format(date);
        }
        return s;
    }
}
