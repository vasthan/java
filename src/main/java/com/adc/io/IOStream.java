package com.adc.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class IOStream {
    public static void main(String[] args) throws IOException {
        var fin = new FileInputStream("run.txt");
        byte b = (byte) fin.read();
        System.out.println((char)b);
        fin.close();

        // 组装流
        var din = new DataInputStream(new BufferedInputStream(new FileInputStream("run.txt")));
        int i = din.readInt();
        System.out.println(i);
        din.close();

        var ins = new InputStreamReader(new FileInputStream("run.txt"), StandardCharsets.UTF_8);
        System.out.println(ins.read());
        ins.close();

    }
}
