package com.szh.delay;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.DelayQueue;

/**
 * 任务生产者
 *
 * @author szh
 */
@Slf4j
public class DelayTaskProducer {

    public static DelayQueue<DelayTask> delayQueue;

    /**
     * @param id   业务id
     * @param time 消费时间  单位：秒
     * @param type 业务类型
     */
    public static void delayTask(String id, Long time, Integer type, String dataStr) {
        //创建任务
        DelayTask delayTask = new DelayTask();
        delayTask.setId(id)
                .setTime(System.currentTimeMillis() + time * 1000)
                .setType(type)
                .setDataStr(dataStr);
        log.info("=============入延时队列,{}", delayTask);
        //任务入队
        boolean offer = delayQueue.offer(delayTask);
        if (offer) {
            log.info("=============入延时队列成功,{}", delayQueue);
        } else {
            log.info("=============入延时队列失败");
        }
    }
}
