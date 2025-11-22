package com.szh.task.util;

import com.szh.task.api.ITask;
import com.szh.task.exception.CompileTaskScriptException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class ScriptLoadUtil {

    public static ITask loadScript(File file) {
        try {
            return GroovyCompile.compile(file, ITask.class, ScriptLoadUtil.class.getClassLoader());
        } catch (CompileTaskScriptException e) {
            log.error("un-expect error! e: {}", e);
            return null;
        }
    }

}
