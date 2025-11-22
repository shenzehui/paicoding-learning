package com.szh.task.concurrent;

import groovy.util.logging.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务管理器
 *
 * @author shenzehui
 * @date 2025/11/22
 */
@Slf4j
public class AsyncTaskManager {

    private static ExecutorService taskExecutors =
            new ThreadPoolExecutor(24,
                    30,
                    3,
                    TimeUnit.MINUTES,
                    new LinkedBlockingDeque<>(10),
                    new DefaultThreadFactory("task-execute"),
                    new ThreadPoolExecutor.CallerRunsPolicy()
            );

    public static void addTask(final Runnable runnable) {
        taskExecutors.submit(runnable);
    }

}
