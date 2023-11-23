package com.adc;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Temp {
    public static void main(String[] args) throws InterruptedException {

        /*
        动词：定义，生成，创建，给我一个，写一个
        名词：度量，计算列，DAL，指标
         */

        String regex = "[\\s\\S]*(定义|生成|创建|给我一个|写一个)[\\s\\S]*(度量|计算列|DAL|指标)[\\s\\S]*";

        System.out.println("帮我定义一个指标，单价求和".matches(regex));
        System.out.println("定义一个指标，单价求和,xxxx".matches(regex));
        System.out.println("定义一个DAL，单价求和,xxxx".matches(regex));
        System.out.println("生成一个计算列，单价求和,xxxx".matches(regex));
        System.out.println("创建一个度量，单价求和,xxxx".matches(regex));


    }
}
