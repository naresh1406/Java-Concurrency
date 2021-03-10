package com.naresh.gupta;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Naresh Gupta on 10/3/2021 AD.
 */
public class RaceCondition {
    public static void main(String[] args) {
        Map<String, String> loanedBooks = new HashMap<>();
        // thread-1
        if (!loanedBooks.containsKey("book1")) {
            loanedBooks.put("book1", "user1");
        }

        // thread-2
        if (!loanedBooks.containsKey("book1")) {
            loanedBooks.put("book1", "user2");
        }

        // multiple thread running concurrently
        // thread ordering and scheduling is not guaranteed
        // to fix race condition issue we can use locks

        ReentrantLock lock = new ReentrantLock();
        // thread-1
        lock.lock();
        if (!loanedBooks.containsKey("book1")) {
            loanedBooks.put("book1", "user1");
        }
        lock.unlock();

        // thread-2
        lock.lock();
        if (!loanedBooks.containsKey("book1")) {
            loanedBooks.put("book1", "user2");
        }
        lock.unlock();

        // Even if we use ConcurrentHashMap that will not solve as we have to instructions.
        // To fix using ConcurrentHashMap we can change two instructions into 1 compound instruction
        // putIfAbsent or computeIfAbsent

        // global variable
        Map<String, String> loanedBooksCHM = new ConcurrentHashMap<>();
        // thread-1
        loanedBooksCHM.putIfAbsent("book1", "user1");
        // thread-2
        loanedBooksCHM.putIfAbsent("book1", "user2");

    }
}
