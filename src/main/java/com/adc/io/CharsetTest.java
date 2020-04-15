package com.adc.io;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;

public class CharsetTest {
    public static void main(String[] args) {


        Charset charset = Charset.defaultCharset();
        System.out.println(charset);

        SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
        System.out.println(availableCharsets);

        // 最佳实践用法
        System.out.println(StandardCharsets.UTF_8);
    }
}
