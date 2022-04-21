package com.adc.concurrency.thread;

public class WrongWayProcessInterruptedException {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Task());
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println("go");
                callOtherMethod();
            }
        }

        private void callOtherMethod() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 错误的处理方式，捕获却不处理
                e.printStackTrace();
            }
        }
    }
}
