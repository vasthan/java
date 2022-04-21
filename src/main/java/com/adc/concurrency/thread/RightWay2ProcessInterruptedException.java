package com.adc.concurrency.thread;

public class RightWay2ProcessInterruptedException {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Task());
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("go");
                callOtherMethod();
            }
            System.out.println("中断");
        }

        private void callOtherMethod() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 恢复中断状态
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}
