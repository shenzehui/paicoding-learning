package com.szh.async.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 *
 * @author szh
 */
public class AsyncFactory {

    private static final Logger jobName = LoggerFactory.getLogger("jobName");

    /**
     * 做一些任务
     *
     * @return 任务task
     */
    public static TimerTask doJob() {
        return new TimerTask() {
            @Override
            public void run() {
                // 模拟耗时操作
                try {
                    System.out.println("===异步线程开始执行===");
                    jobName.info("do something ...");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("===异步线程执行完毕===");
            }
        };
    }

    // 更多任务...

}