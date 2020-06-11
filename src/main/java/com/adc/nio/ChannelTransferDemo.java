package com.adc.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class ChannelTransferDemo {
    public static void main(String[] args) throws IOException {

        RandomAccessFile from = new RandomAccessFile("from.txt", "rw");
        FileChannel fromChannel = from.getChannel();

        RandomAccessFile to = new RandomAccessFile("to.txt", "rw");
        FileChannel toChannel = to.getChannel();

        long n = toChannel.transferFrom(fromChannel, 0, fromChannel.size());
        System.out.println("transfer bytes: " + n);
    }
}
