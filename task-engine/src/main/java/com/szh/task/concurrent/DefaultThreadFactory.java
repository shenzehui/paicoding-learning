package com.szh.task.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 *
 * @author shenzehui
 * @date 2025/11/22
 */
public class DefaultThreadFactory implements ThreadFactory {

    private static final AtomicInteger poolNumAto = new AtomicInteger(1);
    private final AtomicInteger threadNumAto = new AtomicInteger(1);
    private final ThreadGroup group;
    private final String namePrefix;

    public DefaultThreadFactory(String pool) {
        if (null == pool || "".equals(pool)) {
            pool = "defaultPool";
        }

        SecurityManager s = System.getSecurityManager();
        // 线程组，默认获取system线程组（父线程组）
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = pool + poolNumAto.getAndIncrement() + "-thread-";
    }


    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(group, r, namePrefix + threadNumAto.getAndIncrement(), 0);
        if (thread.isDaemon()) {
            thread.setDaemon(true);
        }

        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }

        return thread;
    }
}
