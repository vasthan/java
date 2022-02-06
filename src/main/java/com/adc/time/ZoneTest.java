package com.adc.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * 时区
 */
public class ZoneTest {

    public static void main(String[] args) {
        // 获取系统时钟的默认时区
        ZoneId defaultZone = ZoneId.systemDefault();
        System.out.println(defaultZone);

        // 获取所有可用时区
        System.out.println(ZoneId.getAvailableZoneIds());

        // 把LocalDate转换为带时区的ZonedDateTime
        LocalDate date = LocalDate.now();
        ZonedDateTime zonedDate = date.atStartOfDay(ZoneId.systemDefault());
        System.out.println(zonedDate);

        // 把LocalDateTime转换为带时区的ZonedDateTime
        LocalDateTime time = LocalDateTime.now();
        ZonedDateTime zonedDateTime = time.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime);

        // 使用ZoneId将Instant转换为LocalDateTime
        Instant instant = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println(localDateTime);

        // 将LocalDateTime转换为Instant
        localDateTime.toInstant(ZoneOffset.ofHours(8));
    }
}
