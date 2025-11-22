package com.szh.task.api;

/**
 * 任务接口
 *
 * @author shenzehui
 * @date 2025/11/22
 */
public interface ITask {

    default String name(){
        return this.getClass().getName();
    }

    /**
     * 开始执行任务
     */
    void run();

    /**
     * 任务中断
     */
    void interrupt();

}
