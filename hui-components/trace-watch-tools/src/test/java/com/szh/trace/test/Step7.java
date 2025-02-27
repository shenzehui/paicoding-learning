package com.szh.trace.test;

import com.szh.trace.TraceWatch;
import com.szh.trace.recoder.ITraceRecoder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Step7 {

    private static final Logger log = LoggerFactory.getLogger(Step7.class);

    private static Random random = new Random();

    /**
     * 随机睡眠一段时间
     *
     * @param max
     */
    private static void randSleep(String task, int max) {
        randSleepAndRes(task, max);
    }

    private static int randSleepAndRes(String task, int max) {
        int sleepMillSecond = random.nextInt(max);
        try {
            System.out.println(task + "==> 随机休眠 " + sleepMillSecond + "ms");
            Thread.sleep(sleepMillSecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return sleepMillSecond;
    }

    public void subExecute(String task, int max) {
        ITraceRecoder recoder = TraceWatch.getRecoderOrElseSync();
        recoder.async(() -> randSleep(task, max), task + "-1");
        recoder.async(() -> randSleep(task, max), task + "-2");
    }

    @Test
    public void basicUse() {
        try (ITraceRecoder recoder = TraceWatch.startTrace("basicUse")) {
            recoder.sync(() -> subExecute("task1", 200), "task1");
            recoder.async(() -> {
                log.info("task2 内部执行---");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "task2");
        }

        System.out.println("\n======== 上下文中没有Recoder | 开始 ===========\n");
        subExecute("tt", 10);
        System.out.println("\n======== 上下文中没有Recoder | 结束 ===========\n");
    }
}
