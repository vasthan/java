package com.adc.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptibly implements Runnable{
    private static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            lock.lockInterruptibly();
            try {
                try {
                    System.out.println(Thread.currentThread().getName() + " - 获取到了锁");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " - sleep期间被中断");
                }
            } finally {
                System.out.println(Thread.currentThread().getName() + " - 释放了锁");
                lock.unlock();
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " - 获取锁期间被中断");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new LockInterruptibly());
        Thread thread2 = new Thread(new LockInterruptibly());
        thread1.start();
        thread2.start();

        Thread.sleep(2000);
        thread2.interrupt();
    }
}
