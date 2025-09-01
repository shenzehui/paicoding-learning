package com.szh.agent;

import com.szh.agent.trace.TrackContext;
import com.szh.agent.trace.TrackManager;
import net.bytebuddy.asm.Advice;

import java.util.UUID;

public class MyAdvice {

    @Advice.OnMethodEnter()
    public static void enter(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        String linkId = TrackManager.getCurrentSpan();
        if (null == linkId) {
            linkId = UUID.randomUUID().toString();
            TrackContext.setLinkId(linkId);
        }
        String entrySpan = TrackManager.createEntrySpan();
        System.out.println("链路追踪：" + entrySpan + " " + className + "." + methodName);

    }

    @Advice.OnMethodExit()
    public static void exit(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        TrackManager.getExitSpan();
    }

}