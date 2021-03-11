package com.naresh.gupta;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Naresh Gupta on 10/3/2021 AD.
 */
public class MyReentrantLock {
    // default is un-fair for fair thread pass true in constructor
    private static ReentrantLock lock = new ReentrantLock();

    public void booking() {
        Thread t1 = new Thread(() -> accessSharedResource());
        t1.start();
        Thread t2 = new Thread(() -> accessSharedResource());
        t2.start();
        Thread t3 = new Thread(() -> accessSharedResource());
        t3.start();
        Thread t4 = new Thread(() -> accessSharedResource());
        t4.start();
    }

    private void accessSharedResource() {
        // 1. Using lock and unlock
        lock.lock();

        // access the resource

        lock.unlock();

        // 2. Using lock and unlock with try-finally because if there is any exception occur during accessing resource
        // lock will not unlock so using finally will help to that in any situation

        try {
            lock.lock();

            // access the resource

        } finally {
            lock.unlock();
        }

        // 3. Another way to solve this using synchronized block
        synchronized (this) {

            // access the resource

        }
    }


}
