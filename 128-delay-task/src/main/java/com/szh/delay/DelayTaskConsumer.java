package com.szh.delay;

import cn.hutool.core.thread.ExecutorBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消费端
 *
 * @author shz
 */
@Slf4j
@Component
public class DelayTaskConsumer implements CommandLineRunner {

    @Autowired(required = false)
    private DelayTaskHandler delayTaskHandler;

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {
        private final ThreadFactory defaultFactory = Executors.defaultThreadFactory();
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = this.defaultFactory.newThread(r);
            if (!thread.isDaemon()) {
                thread.setDaemon(true);
            }

            thread.setName("delay-" + this.threadNumber.getAndIncrement());
            return thread;
        }
    };

    private final ExecutorService pool = new ExecutorBuilder()
            .setCorePoolSize(2)
            .setMaxPoolSize(5)
            .setKeepAliveTime(30)
            .setKeepAliveTime(5, TimeUnit.SECONDS)
            .setWorkQueue(new SynchronousQueue<Runnable>())
            .setHandler(new ThreadPoolExecutor.CallerRunsPolicy())
            .setThreadFactory(THREAD_FACTORY)
            .buildFinalizable();

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run(String... args) {
        //获取同一个put进去任务的队列
        DelayQueue<DelayTask> delayQueue = DelayTaskQueue.getInstance();
        DelayTaskProducer.delayQueue = delayQueue;
        pool.execute(
                () -> {
                    for (; ; ) {
                        // 从延迟队列的头部获取已经过期的消息
                        // 如果暂时没有过期消息或者队列为空，则take()方法会被阻塞，直到有过期的消息为止
                        DelayTask delayTask = null;
                        try {
                            //阻塞
                            delayTask = delayQueue.take();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        delayTaskHandler.dealTask(delayTask);
                    }
                }
        );
    }
}
