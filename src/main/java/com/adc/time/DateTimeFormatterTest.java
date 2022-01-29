package com.adc.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * 日期时间的解析和格式化
 */
public class DateTimeFormatterTest {

    public static void main(String[] args) {
        DateTimeFormatter basic = DateTimeFormatter.ISO_LOCAL_DATE;

        // 创建一个ITALIAN Formatter
        DateTimeFormatter italian1 = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);

        // 使用DateTimeFormatterBuilder创建更复杂的Formatter
        DateTimeFormatter italian2 = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);

        LocalDate now = LocalDate.now();
        System.out.println(now.format(basic));
        System.out.println(now.format(italian1));
        System.out.println(now.format(italian2));
    }
}
