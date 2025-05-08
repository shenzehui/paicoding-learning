package com.szh.delay;

import java.util.concurrent.DelayQueue;

/**
 * 延时队列单例
 *
 * @author szh
 */
public class DelayTaskQueue {
    private static class Holder {
        //单例保证队列唯一
        static DelayQueue<DelayTask> instance = new DelayQueue<>();
    }

    public static DelayQueue<DelayTask> getInstance() {
        return Holder.instance;
    }
}
