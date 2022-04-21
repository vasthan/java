package com.adc.concurrency.thread;

public class RightWay1ProcessInterruptedException {

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
                try {
                    callOtherMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("中断");
                    break;
                }
            }
        }

        /**
         * 传递异常让上层处理
         * @throws InterruptedException
         */
        private void callOtherMethod() throws InterruptedException {
            Thread.sleep(1000);
        }
    }
}
