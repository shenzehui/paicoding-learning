package com.szh.trace.test;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalExample {
    private static final ThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        threadLocal.set("t");
        System.out.println("父线程的值:" + threadLocal.get());

        executor.submit(() -> {
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            executorService = TtlExecutors.getTtlExecutorService(executorService);
            threadLocal.set("t1");
            executorService.submit(() -> {
                System.out.println("子线程获取的值：" + threadLocal.get());
            });

        });

        executor.submit(() -> {
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            executorService = TtlExecutors.getTtlExecutorService(executorService);
            threadLocal.set("t2");
            executorService.submit(() -> {
                System.out.println("子线程获取的值：" + threadLocal.get());
            });
        });

        executor.submit(() -> {
            threadLocal.set("t3");
            System.out.println("子线程获取的值：" + threadLocal.get());
        });

        executor.submit(() -> {
            threadLocal.set("t4");
            System.out.println("子线程获取的值：" + threadLocal.get());
        });

        executor.submit(() -> {
            threadLocal.set("t5");
            System.out.println("子线程获取的值：" + threadLocal.get());
        });

    }
}