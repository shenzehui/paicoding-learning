package com.szh.trace.test;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.Closeable;
import java.util.Random;
import java.util.function.Supplier;

/**
 * 阶段二：在第一阶段基础上进一步封装，减少代码冗余
 */
public class Step2 {

    private static Random random = new Random();

    /**
     * 随机睡眠一段时间
     *
     * @param max
     */
    public static void randSleep(int max) {
        int sleepMillSecond = random.nextInt(max);
        try {
            System.out.println("随机休眠 " + sleepMillSecond + "ms");
            Thread.sleep(sleepMillSecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCost() {
        StopWatch stopWatch = new StopWatch("测试耗时");
        // 注意这里的第一个randSleep的耗时，不会被记录再StopWatch中
        randSleep(300);
        stopWatch.start("task1");
        randSleep(100);
        stopWatch.stop();

        stopWatch.start("task2");
        randSleep(30);
        stopWatch.stop();

        stopWatch.start("task3");
        randSleep(50);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }

    @Test
    public void testCost2() {
        randSleep(300);
        try (StopWatchWrapper stopWatchWrapper = StopWatchWrapper.instance("测试耗时")) {
            randSleep(300);
            stopWatchWrapper.cost(() -> randSleep(100), "task1");

            String res = stopWatchWrapper.cost(() -> {
                randSleep(30);
                return "hello";
            }, "task2");
            System.out.println("task2测试返回：" + res);

            stopWatchWrapper.cost(() -> randSleep(50), "task3");
        }
    }

    /**
     * 一个简单的封装，减少冗余代码对业务代码的侵入
     */
    public static class StopWatchWrapper implements Closeable {

        private StopWatch stopWatch;

        public static StopWatchWrapper instance(String task) {
            StopWatchWrapper stopWatchWrapper = new StopWatchWrapper();
            stopWatchWrapper.stopWatch = new StopWatch(task);
            return stopWatchWrapper;
        }

        public void cost(Runnable runnable, String task) {
            stopWatch.start(task);
            try {
                runnable.run();
            } finally {
                stopWatch.stop();
            }
        }

        public <T> T cost(Supplier<T> sup, String task) {
            stopWatch.start(task);
            try {
                return sup.get();
            } finally {
                stopWatch.stop();
            }
        }

        @Override
        public void close() {
            System.out.println(stopWatch.prettyPrint());
        }
    }
}
