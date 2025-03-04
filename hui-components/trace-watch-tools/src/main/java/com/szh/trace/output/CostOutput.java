package com.szh.trace.output;

import java.util.Map;

@FunctionalInterface
public interface CostOutput {

    /**
     * 输出
     *
     * @param cost      任务耗时分布
     * @param traceName Trace
     */
    void output(Map<String, Long> cost, String traceName);

}
