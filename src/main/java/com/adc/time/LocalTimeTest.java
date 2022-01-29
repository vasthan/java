package com.adc.time;

import java.time.LocalTime;

/**
 * LocalTime表示一个时间，例如12:55:55
 */
public class LocalTimeTest {
    public static void main(String[] args) {

        // 使用静态方法of创建LocalTime实例
        LocalTime time = LocalTime.of(12, 55, 55);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.printf("hour: %s, minute: %s, second: %s\n", hour, minute, second);

        // 获取代表当前时间的LocalTime实例
        LocalTime now = LocalTime.now();
        System.out.println("now: " + now);

        // 通过解析字符串的方式获取LocalTime实例
        LocalTime parseTime = LocalTime.parse("12:55:55");
        System.out.println("parseTime: " + parseTime);
    }
}
