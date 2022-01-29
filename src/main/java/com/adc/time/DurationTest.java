package com.adc.time;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Duration表示一个时间长度
 * Duration以秒和纳秒衡量时间的长度
 * 可以用来计算
 *      1. 两个LocalTime之间的Duration
 *      2. 两个LocalDateTime之间的Duration
 *      3. 两个Instant之间的Duration
 * 不可以计算
 *      1. LocalDate之间的Duration
 *
 * 若要表达日期的长度，可以使用Period类
 */
public class DurationTest {
    public static void main(String[] args) {

        // 一、通过between方式创建Duration

        // 创建两个LocalTime之间的Duration
        LocalTime time1 = LocalTime.of(12, 55, 55);
        LocalTime time2 = LocalTime.of(13, 55, 55);
        System.out.println(Duration.between(time1, time2).getSeconds());

        // 创建两个LocalDateTime之间的Duration
        LocalDateTime localDateTime1 = time1.atDate(LocalDate.of(2022, 1, 29));
        LocalDateTime localDateTime2 = time2.atDate(LocalDate.of(2022, 1, 30));
        System.out.println(Duration.between(localDateTime1, localDateTime2).getSeconds());

        // 创建两个Instant之间的Duration
        Instant instant1 = Instant.ofEpochSecond(1000);
        Instant instant2 = Instant.ofEpochSecond(3000);
        System.out.println(Duration.between(instant1, instant2).getSeconds());

        // 二、通过of方法创建Duration
        Duration threeHours = Duration.ofHours(3);
        Duration tenSeconds = Duration.of(10, ChronoUnit.SECONDS);
        System.out.println(threeHours.getSeconds());
        System.out.println(tenSeconds.getSeconds());
    }
}
