package com.naresh.gupta;

/**
 * Created by Naresh Gupta on 11/3/2021 AD.
 */
public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName() + " running"));
        thread.start();

        MyThread myThread = new MyThread();
        myThread.start();

        System.out.println("------------------------------------------------------------------------------------------");

        Thread myRunnable = new Thread(new MyRunnable(), "MyRunnable Thread");
        myRunnable.start();

        System.out.println("------------------------------------------------------------------------------------------");

        // Anonymous Runnable
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " running");
                System.out.println(Thread.currentThread().getName() + " finished");
            }
        };

        Thread anonymousThread = new Thread(runnable, "Anonymous Thread");
        anonymousThread.start();

        System.out.println("------------------------------------------------------------------------------------------");

        Runnable lambdaRunnable = () -> {
            System.out.println(Thread.currentThread().getName() + " running");
            System.out.println(Thread.currentThread().getName() + " finished");
        };

        Thread lambdaThread = new Thread(lambdaRunnable, "Lambda Thread");
        lambdaThread.start();

        System.out.println("------------------------------------------------------------------------------------------");

        // Thread Sleep
        Runnable sleepRunnable = () -> {
            System.out.println(Thread.currentThread().getName() + " running");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        };

        Thread sleepThread = new Thread(sleepRunnable, "Sleep Thread");
        sleepThread.start();

        System.out.println("------------------------------------------------------------------------------------------");

        StoppableRunnable stoppableRunnable = new StoppableRunnable();
        Thread stopRunnableThread = new Thread(stoppableRunnable, "StopRunnable Thread");
        stopRunnableThread.start();

        // sleep main thread
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Requesting stop");
        stoppableRunnable.requestStop();
        System.out.println("stop requested");
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " running");
            System.out.println(Thread.currentThread().getName() + " finished");
        }
    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " running");
            System.out.println(Thread.currentThread().getName() + " finished");
        }
    }

    public static class StoppableRunnable implements Runnable {
        private static boolean stopRequested = false;

        public synchronized void requestStop() {
            this.stopRequested = true;
        }

        public synchronized boolean isStopRequested() {
            return this.stopRequested;
        }

        private void sleep(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            System.out.println("StoppableRunnable running");
            while (!isStopRequested()) {
                sleep(1000);
                System.out.println("...");
            }
            System.out.println("StoppableRunnable finished");
        }
    }
}
