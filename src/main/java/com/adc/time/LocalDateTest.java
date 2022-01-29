package com.adc.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;

/**
 * LocalDate表示一个日期，例如2022-01-29
 */
public class LocalDateTest {
    public static void main(String[] args) {
        // 使用静态方法of创建LocalDate实例
        LocalDate date = LocalDate.of(2022, 1, 29);
        int year = date.getYear();
        Month month = date.getMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayOfMonth = date.getDayOfMonth();
        int dayOfYear = date.getDayOfYear();

        System.out.printf("year: %s, month: %s, dayOfWeek: %s, dayOfMonth: %s, dayOfYear: %s\n",
                year, month, dayOfWeek, dayOfMonth, dayOfYear);

        // 获取代表当前日期的LocalDate实例
        LocalDate now = LocalDate.now();
        System.out.printf("now: %s\n", now);

        System.out.println(now.get(ChronoField.YEAR));
        System.out.println(now.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(now.get(ChronoField.DAY_OF_MONTH));

        // 通过解析日期字符串获取LocalDate实例
        LocalDate parseDate = LocalDate.parse("2022-01-29");
        System.out.println("parseResult:" + parseDate);
    }
}
