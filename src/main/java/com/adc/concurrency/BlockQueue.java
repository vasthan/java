package com.adc.concurrency;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueue<E> {

    private LinkedList<E> data = new LinkedList<>();
    private int maxCapacity = 10;
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public void enqueue(E e) {
        lock.lock();
        try {
            while (isFull()) {
                notFull.await();
            }
            data.addLast(e);
            notEmpty.signal();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        } finally {
            lock.unlock();
        }
    }

    public E dequeue() {
        lock.lock();
        try {
            while (isEmpty()) {
                notEmpty.await();
            }
            E e = data.removeFirst();
            notFull.signal();
            return e;
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        } finally {
            lock.unlock();
        }
    }

    private boolean isFull() {
        return data.size() == maxCapacity;
    }

    private boolean isEmpty() {
        return data.size() == 0;
    }
}
