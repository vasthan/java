package com.code.lambda;

import com.adc.model.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    private List<Transaction> transactions;

    @Before
    public void before() {
        transactions = new ArrayList<>();
        transactions.add(new Transaction("CNY", 100));
        transactions.add(new Transaction("CNY", 500));
        transactions.add(new Transaction("CNY", 1000));
        transactions.add(new Transaction("CNY", 1200));
        transactions.add(new Transaction("USD", 300));
        transactions.add(new Transaction("USD", 500));
        transactions.add(new Transaction("USD", 700));
        transactions.add(new Transaction("USD", 900));
        transactions.add(new Transaction("USD", 1100));
        transactions.add(new Transaction("HKD", 600));
        transactions.add(new Transaction("HKD", 800));
        transactions.add(new Transaction("HKD", 1000));
        transactions.add(new Transaction("HKD", 1200));
    }

    /**
     * 顺序流
     */
    @Test
    public void streamTest() {
        List<Transaction> result = transactions.stream().filter(transaction -> transaction.getPrice() >= 1000)
                .collect(Collectors.toList());
        System.out.println("price大于等于1000: " + result);

        Map<String, List<Transaction>> result2 = transactions.stream().filter(transaction -> transaction.getPrice() >= 1000)
                .collect(Collectors.groupingBy(Transaction::getCurrency));
        System.out.println("price大于等于1000且按货币分组: " + result2);
    }

    /**
     * 并行流
     */
    @Test
    public void parallelStreamTest() {
        List<Transaction> result = transactions.parallelStream().filter(transaction -> transaction.getPrice() >= 1000)
                .collect(Collectors.toList());
        System.out.println("price大于等于1000: " + result);
    }

    /**
     * 利用无限流求累加
     */
    @Test
    public void sequentialSum() {
        int n = 100;
        final Long sum = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .collect(Collectors.summingLong(Long::longValue));
        System.out.println(sum);
    }

}
