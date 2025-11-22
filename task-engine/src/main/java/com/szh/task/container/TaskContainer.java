package com.szh.task.container;

import com.szh.task.concurrent.AsyncTaskManager;
import com.szh.task.task.ScriptTaskDecorate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 任务容器
 *
 * @author shenzehui
 * @date 2025/11/22
 */
public class TaskContainer {

    /**
     * key：com.szh.task.api.ITask#name()
     */
    private static Map<String, ScriptTaskDecorate> taskCache = new ConcurrentHashMap<>();

    /**
     * 脚本绝对路径
     */
    private static Map<String, ScriptTaskDecorate> pathCache = new ConcurrentHashMap<>();

    public static void registerTask(String path, ScriptTaskDecorate task) {
        ScriptTaskDecorate origin = taskCache.get(task.getName());
        if (origin != null) {
            origin.interrupt();
        }
        taskCache.put(task.getName(), task);
        pathCache.put(path, task);
        AsyncTaskManager.addTask(task);
    }

    public static void removeTask(String path) {
        ScriptTaskDecorate task = pathCache.get(path);
        if (task != null) {
            task.interrupt();
            taskCache.remove(task.getName());
            pathCache.remove(path);
        }
    }

}
