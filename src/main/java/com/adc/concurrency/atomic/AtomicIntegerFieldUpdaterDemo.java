package com.adc.concurrency.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 普通变量升级为原子变量
 */
public class AtomicIntegerFieldUpdaterDemo {
    private static final AtomicIntegerFieldUpdater<Student> updater = AtomicIntegerFieldUpdater.newUpdater(Student.class, "score");
    private static final Student tom = new Student();
    private static final Student peter = new Student();

    public static class Student {
        volatile int score;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Task());
        Thread thread2 = new Thread(new Task());
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("tom: " + tom.score);
        System.out.println("peter: " + peter.score);
    }

    public static class Task implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                tom.score++;
                updater.getAndIncrement(peter);
            }
        }
    }
}
