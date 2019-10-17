package com.code.lambda;

import com.adc.function.FileProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAroundTest {
    public static void main(String[] args) throws IOException {
        String content = processFile("/users/yanghanqing/.bash_profile", br -> br.readLine());
        System.out.println(content);

    }

    private static String processFile(String filePath, FileProcessor fileProcessor) throws IOException {
        try (
            BufferedReader br = new BufferedReader(new FileReader(filePath))
        ) {
            return fileProcessor.process(br);
        }
    }
}
