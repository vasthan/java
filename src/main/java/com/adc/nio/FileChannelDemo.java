package com.adc.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("run.txt", "rw");
        FileChannel channel = raf.getChannel();
        

        ByteBuffer buf = ByteBuffer.allocate(100);

        int n = 0;
        do {
            // 数据从channel写入buffer
            n = channel.read(buf);
            System.out.println("read bytes: " + n);

            // 翻转buffer，进入读模式
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.println((char)buf.get());
            }
            buf.clear();
            buf.compact();
        } while (n > 0);
        raf.close();
    }
}
