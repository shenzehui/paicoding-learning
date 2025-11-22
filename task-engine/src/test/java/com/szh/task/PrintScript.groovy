package com.szh.task

import com.szh.task.api.ITask

class PrintScript implements ITask {
    @Override
    void run() {
        println "print script run"
    }

    @Override
    void interrupt() {
        println "print script over!"
    }
}
