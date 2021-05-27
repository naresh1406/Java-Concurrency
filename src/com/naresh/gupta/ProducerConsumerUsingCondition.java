package com.naresh.gupta;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Naresh Gupta on 12/3/2021 AD.
 */
public class ProducerConsumerUsingCondition<E> {
    private int max;
    private Queue<E> queue;
    private Lock lock;
    private Condition notEmpty;
    private Condition notFull;

    public ProducerConsumerUsingCondition(int size) {
        this.max = size;
        this.queue = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    public void put(E e) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == max)
                notFull.wait();
            queue.add(e);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty())
                notEmpty.wait();
            E item = queue.poll();
            notFull.signalAll();
            return item;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerUsingCondition<Integer> obj = new ProducerConsumerUsingCondition<>(5);

    }
}
