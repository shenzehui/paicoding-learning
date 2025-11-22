package com.szh.task

import com.szh.task.api.ITask

class DemoScript implements ITask {
    @Override
    void run() {
        println "now > : >>" + System.currentTimeMillis()
    }

    @Override
    void interrupt() {
        println "原来的结束 over"
    }
}
