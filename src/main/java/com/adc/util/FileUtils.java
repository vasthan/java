package com.adc.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author 拓破
 */
public class FileUtils {

    public static String readAllLines(String filePath) throws IOException {
        StringBuilder res = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            res.append(line);
        }
        return res.toString();
    }
}
