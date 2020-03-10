package com.adc.concurrency;

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
        // synchronized修饰的方法编译后会有ACC_SYNCHRONIZED标记
        @Override
        public synchronized void increment() {
            count++;
        }

        public void increment2() {
            // synchronized修饰的代码块编译后会在临界区前后插入monitorenter和monitorexit指令
            synchronized (this) {
                count++;
            }
        }

        @Override
        public synchronized int get() {
            return super.get();
        }
    }

    public static void main(String[] args) {
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
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.get());
    }
}
