package com.adc.concurrency;

import java.util.concurrent.*;

public class CyclicBarrierTest {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<Character> q1 = new LinkedBlockingDeque();
        LinkedBlockingDeque<Character> q2 = new LinkedBlockingDeque();

        ExecutorService executor = Executors.newFixedThreadPool(1);
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            executor.execute(() -> {
                char s1 = q1.removeFirst();
                char s2 = q2.removeFirst();
                System.out.printf("%s -> %s\n", s1, s2);
            });
        });

        Thread t1 = new Thread(() -> {
            char[] lower = {'a', 'b', 'c', 'd', 'e'};
            for (int i = 0; i < lower.length; i++) {
                try {
                    q1.addLast(lower[i]);
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            char[] upper = {'A', 'B', 'C', 'D', 'E'};
            for (int i = 0; i < upper.length; i++) {
                try {
                    q2.addLast(upper[i]);
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        executor.shutdown();
    }

}
