package com.adc.visible;

/**
 * 多线程内存可见性测试
 */
public class TestVisibility {

    private static class Counter {
        int count;
        public void increment() {
            count++;
        }
        public int get() {
            return count;
        }
    }

    private static class ThreadSafeCounter extends Counter{
        @Override
        public synchronized void increment() {
            count++;
        }

        @Override
        public synchronized int get() {
            return super.get();
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        Counter counter = new Counter();
        Counter counter = new ThreadSafeCounter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter.get());
    }
}
