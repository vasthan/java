package com.adc.time;

import java.time.Instant;

/**
 * Instant表示一个时刻、一个瞬间
 * 它以计算机易理解的方式对时间进行建模
 * 它以Unix元年时间（UTC 1970-01-01 00:00:00）开始所经历的秒数进行计算
 */
public class InstantTest {

    public static void main(String[] args) {
        // 1970-01-01 00:00:00 加上 3秒
        System.out.println(Instant.ofEpochSecond(3));
        // 1970-01-01 00:00:00 加上 2秒，再加上1_000_000_000纳秒
        System.out.println(Instant.ofEpochSecond(2, 1_000_000_000));
        // 1970-01-01 00:00:00 加上 4秒，再减去1_000_000_000纳秒
        System.out.println(Instant.ofEpochSecond(4, -1_000_000_000));
    }
}
