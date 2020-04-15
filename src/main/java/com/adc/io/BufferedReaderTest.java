package com.adc.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class BufferedReaderTest {
    public static void main(String[] args) throws IOException {

        FileInputStream fin = new FileInputStream("run.txt");
        InputStreamReader reader = new InputStreamReader(fin, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(reader);
        br.lines().forEach(System.out::println);
        br.close();
    }
}
