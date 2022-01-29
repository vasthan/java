package com.adc.time;

import java.time.LocalDate;
import java.time.Period;

/**
 * 类似于Duration，Period表达的是日期的长度
 */
public class PeriodTest {

    public static void main(String[] args) {

        // 创建两个LocalDate之间的Period
        LocalDate date1 = LocalDate.of(2021, 12, 9);
        LocalDate date2 = LocalDate.of(2022, 1, 29);
        Period period = Period.between(date1, date2);
        System.out.println(period);

        // 通过of方法创建Period
        Period threeYears = Period.ofYears(3);
        Period tenDays = Period.ofDays(10);
    }
}
