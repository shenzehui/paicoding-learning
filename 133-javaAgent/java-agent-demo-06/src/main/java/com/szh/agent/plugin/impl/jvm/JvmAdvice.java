package com.szh.agent.plugin.impl.jvm;

import net.bytebuddy.asm.Advice;

public class JvmAdvice {

    @Advice.OnMethodExit()
    public static void exit() {
        JvmStack.printMemoryInfo();
        JvmStack.printGCInfo();
    }

}
