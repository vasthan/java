package com.adc.time;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * LocalDateTime表示日期+时间，如2022-01-29 12:55:55
 */
public class LocalDateTimeTest {
    public static void main(String[] args) {
        // 通过LocalDateTime.of()创建实例
        LocalDateTime dateTime1 = LocalDateTime.of(2022, 1, 29, 12, 55, 55);
        System.out.println(dateTime1);

        // 通过合并LocalDate和LocalTime的方式创建
        LocalDate date = LocalDate.of(2022, 1, 29);
        LocalTime time = LocalTime.of(12, 55, 55);
        System.out.println(LocalDateTime.of(date, time));
        System.out.println(date.atTime(time));
        System.out.println(time.atDate(date));

        // 获取表示当前日期时间的LocalDateTime实例
        LocalDateTime now = LocalDateTime.now(Clock.tickSeconds(ZoneId.systemDefault()));
        System.out.println("now: " + now);

        // 从LocalDateTime实例中提取LocalDate和LocalTime
        System.out.println(now.toLocalDate());
        System.out.println(now.toLocalTime());
    }
}
