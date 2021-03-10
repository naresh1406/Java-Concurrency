package com.naresh.gupta;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Naresh Gupta on 10/3/2021 AD.
 */
public class Concurrency {
    private static int ticketAvailable; // shared variable

    public static void main(String[] args) throws InterruptedException {
        // Two thread using common shared variable which will lead in concurrency problem
        new Thread(() -> {
            if (ticketAvailable > 0) {
                bookTicket();
                ticketAvailable--;
            }
        }).start();

        new Thread(() -> {
            if (ticketAvailable > 0) {
                bookTicket();
                ticketAvailable--;
            }
        }).start();

        Thread.sleep(5000);

        // To fix this we can use lock once a thread acquire lock do all the steps
        // then only other thread will get access to share variable.

        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            if (ticketAvailable > 0) {
                bookTicket();
                ticketAvailable--;
            }
            lock.unlock();
        }).start();

        new Thread(() -> {
            lock.lock();
            if (ticketAvailable > 0) {
                bookTicket();
                ticketAvailable--;
            }
            lock.unlock();
        }).start();

        Thread.sleep(5000);
    }

    private static void bookTicket() {
        System.out.println("Booking ticket...");
    }
}
