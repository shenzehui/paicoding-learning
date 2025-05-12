package com.szh.delay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 任务处理实现类
 *
 * @author szh
 */
@Component
@Slf4j
public class DelayTaskHandlerImpl implements DelayTaskHandler {

    // 具体任务消费处理
    @Override
    public void dealTask(DelayTask delayTask) {
        log.info("消费任务开始---------------");
        log.info("打印任务对象：{}", delayTask.toString());
        log.info("消费任务结束---------------");
    }
}
