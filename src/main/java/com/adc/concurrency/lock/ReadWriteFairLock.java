package com.adc.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteFairLock {
    private static ReadWriteLock rw = new ReentrantReadWriteLock(true);
    private static Lock readLock = rw.readLock();
    private static Lock writeLock = rw.writeLock();

    public static void main(String[] args) {
        readLock.lock();
        try {

        } finally {
            readLock.unlock();
        }
    }
}
