package com.szh.task.task;

import com.szh.task.api.ITask;
import lombok.extern.slf4j.Slf4j;

/**
 * 脚本任务装饰类
 *
 * @author shenzehui
 * @date 2025/11/22
 */
@Slf4j
public class ScriptTaskDecorate extends Thread{

    private ITask task;

    public ScriptTaskDecorate(ITask task) {
        this.task = task;
        // 线程名称
        setName(task.name());
    }

    @Override
    public void run() {
        try {
            task.run();
        } catch (Exception e) {
            log.error("script task run error! task: {}", task.name());
        }
    }

    @Override
    public void interrupt() {
        task.interrupt();
    }
}
