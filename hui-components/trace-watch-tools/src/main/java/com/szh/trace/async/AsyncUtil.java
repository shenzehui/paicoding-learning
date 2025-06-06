package com.szh.trace.async;


import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 异步工具类
 */
public class AsyncUtil {

    public static ExecutorService executorService;

    static {
        executorService = initExecutorService(Runtime.getRuntime().availableProcessors() * 2, 50);
    }

    public static ExecutorService initExecutorService(int core, int max) {
        // 异步工具类的默认线程池构建
        max = Math.max(core, max);
        ThreadFactory THREAD_FACTORY = new ThreadFactory() {
            private final ThreadFactory defaultFactory = Executors.defaultThreadFactory();
            private final AtomicInteger threadNumber = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = this.defaultFactory.newThread(runnable);
                if (!thread.isDaemon()) {
                    thread.setDaemon(true);
                }

                thread.setName("trace-watch-dog-" + this.threadNumber.getAndIncrement());
                return thread;
            }
        };
        ExecutorService executorService = new ThreadPoolExecutor(core, max, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), THREAD_FACTORY, new ThreadPoolExecutor.CallerRunsPolicy());
        // 包装一下线程池，避免出现上下文复用场景
        return TtlExecutors.getTtlExecutorService(executorService);
    }

}
