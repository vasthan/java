package com.adc.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * Temporal接口提供了with、plus、minus等修改日期时间的操作
 *
 * 对于更加复杂的操作，可以使用TemporalAdjuster类实现定制化
 *
 * TemporalAdjusters提供了大量预定义的TemporalAdjuster
 */
public class TemporalAdjusterTest {

    public static void main(String[] args) {

        // 使用with、plus、minus等方法修改日期
        LocalDate now = LocalDate.now();
        LocalDate date1 = now.plus(10, ChronoUnit.DAYS);
        LocalDate date2 = now.minusYears(1);
        LocalDate date3 = now.withMonth(3);
        System.out.println(now);
        System.out.println("plus 10 days: " + date1);
        System.out.println("minus 1 year: " + date2);
        System.out.println("set month to 3: " + date3);

        // 使用TemporalAdjuster调整日期
        LocalDate date = LocalDate.now();
        System.out.println("当前日期: " + date);

        LocalDate secondMonday = date.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.MONDAY));
        System.out.println("当月的第2个周一：" + secondMonday);

        LocalDate firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("当月的第1天：" + firstDayOfMonth);

        LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("当月的最后1天：" + lastDayOfMonth);

        LocalDate firstDayOfNextMonth = date.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println("下个月的第1天：" + firstDayOfNextMonth);

        LocalDate firstDayOfYear = date.with(TemporalAdjusters.firstDayOfYear());
        System.out.println("当年的第1天：" + firstDayOfYear);

        LocalDate lastDayOfYear = date.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("当年的最后1天：" + lastDayOfYear);

        LocalDate firstDayOfNextYear = date.with(TemporalAdjusters.firstDayOfNextYear());
        System.out.println("明年的第1天：" + firstDayOfNextYear);

        LocalDate firstDayOfWeekInMonth = date.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
        System.out.println("当月的第1个周日：" + firstDayOfWeekInMonth);

        LocalDate lastDayOfWeekInMonth = date.with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
        System.out.println("当月的最后1个周日：" + lastDayOfWeekInMonth);

        // next不考虑当天，nextOrSame考虑当天
        LocalDate nextDayOfWeek = date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        System.out.println("下一个周六：" + nextDayOfWeek);

        // previous考虑当天，nextOrSame不考虑当天
        LocalDate previousDayOfWeek = date.with(TemporalAdjusters.previous(DayOfWeek.SATURDAY));
        System.out.println("上一个周六：" + previousDayOfWeek);

        LocalDate nextWorkingDay = date.with(new NextWorkingDay());
        System.out.println("下一个工作日：" + nextWorkingDay);

    }

    public static class NextWorkingDay implements TemporalAdjuster {
        @Override
        public Temporal adjustInto(Temporal temporal) {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }
    }
}
