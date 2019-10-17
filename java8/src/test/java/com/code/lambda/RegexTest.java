package com.code.lambda;

import org.junit.Test;

public class RegexTest {

    @Test
    public void test() {

        String s = "0.123";
        System.out.println(s.matches("0|[1-9]{1,4}|0\\.\\d{1,2}"));


    }
}
