package com.adc.concurrency.lock;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    static AtomicReference<Thread> ref = new AtomicReference<>();
    static SpinLock lock = new SpinLock();

    public void lock() {
        while (!ref.compareAndSet(null, Thread.currentThread()))
            System.out.println(Thread.currentThread().getName() + " 获取锁失败，再次尝试");
    }

    public void unlock() {
        ref.compareAndSet(Thread.currentThread(), null);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Task());
        Thread thread2 = new Thread(new Task());
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
    }

    public static class Task implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 获取锁成功");
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " 释放了锁");
                lock.unlock();
            }
        }
    }
}
