package com.szh.task;

import com.szh.task.api.ITask;
import com.szh.task.container.TaskContainer;
import com.szh.task.task.ScriptTaskDecorate;
import com.szh.task.util.FileUtils;
import com.szh.task.util.ScriptLoadUtil;
import com.szh.task.watch.TaskChangeWatcher;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;
import java.util.function.Predicate;

/**
 * 脚本执行引擎
 *
 * @author shenzehui
 * @date 2025/11/22
 */
@Slf4j
public class ScriptExecuteEngine {
    private static final String SCRIPT_TYPE = ".groovy";
    private static final String SOURCE_PATH =
            "/Users/lenve/projects/paicoding-learning/task-engine/src/test/java/com/szh/task";


    public void run(String source) {
        List<File> scripts = FileUtils.loadFiles(source, new Predicate<File>() {
            @Override
            public boolean test(File file) {
                return !file.getName().endsWith(SCRIPT_TYPE);
            }
        });

        for (File file : scripts) {
            ITask iTask = ScriptLoadUtil.loadScript(file);
            if(iTask == null){
                continue;
            }
            ScriptTaskDecorate scriptTask = new ScriptTaskDecorate(iTask);
            // 注册任务
            TaskContainer.registerTask(file.getAbsolutePath(), scriptTask);
        }

        boolean isSuccess = TaskChangeWatcher.registerWatcher(new File(source));
        if(!isSuccess){
            log.error("register watcher for script task change error! source: {}", source);
            System.exit(1);
        }

    }

    private static volatile boolean run = true;

    public static void main(String[] args) throws InterruptedException {
        new ScriptExecuteEngine().run(SOURCE_PATH);
        registerHook();
        while (run) {
            Thread.sleep(1000 * 10 * 10);
        }
        log.info("application over!!!!");
    }

    /**
     * 注册一个程序关闭的钩子, 用于回收现场
     */
    private static void registerHook() {
        Runtime.getRuntime().addShutdownHook(new Thread("shutdown-thread") {
            @Override
            public void run() {
                log.info("closing Application......");
                run = false;
                log.info("closing over........");
            }
        });
    }


}
