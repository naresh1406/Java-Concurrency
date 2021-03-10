package com.naresh.gupta;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Naresh Gupta on 10/3/2021 AD.
 */
public class Parallelism {
    public static void main(String[] args) {
        // Java thread using Runnable interface run method
        new Thread(new Runnable() {
            @Override
            public void run() {
                processTax("user1");
            }
        }).start(); // task-1

        // Java-8 style using lambda
        new Thread(() -> processTax("user2")).start(); // task-2

        heavyCalculations();


        // Using ThreadPool

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(() -> processTax("user1"));
        executorService.submit(() -> processTax("user2"));

        heavyCalculations();
    }

    private static void heavyCalculations() {
    }

    private static void processTax(String user) {
        System.out.println("Processing tax for user " + user);
    }
}
