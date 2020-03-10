package com.adc.concurrency;

import java.util.List;

// 单例对象
public class Allocator {
    private List<Object> als;

    // 用于一次性申请资源
    public synchronized void apply(Object from, Object to) {
        // 经典范式 while(条件不满足) wait();
        while (als.contains(from) || als.contains(to)) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        als.add(from);
        als.add(to);
    }

    // 归还资源
    public synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
        this.notifyAll();
    }
}
