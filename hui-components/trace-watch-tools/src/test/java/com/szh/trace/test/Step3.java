package com.szh.trace.test;

import org.junit.Test;

import java.text.NumberFormat;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实现一个并发安全的工具类
 */
public class Step3 {
    public static class TraceWatch {

        /**
         * 任务名
         */
        private String taskName;

        /**
         * 子任务耗时时间
         */
        private Map<String, Long> taskCost;

        public TraceWatch(String taskName) {
            this.taskName = taskName;
            this.taskCost = new ConcurrentHashMap<>();
        }

        public TraceWatch() {
            this("");
        }

        public void start(String taskName) {
            taskCost.put(taskName, System.currentTimeMillis());
        }


        public void stop(String taskName) {
            Long start = taskCost.get(taskName);
            if (start == null || start < 946656000L) {
                // last = null -> 兼容没有使用开始，直接调用了结束的场景
                // last 存的是耗时而非时间戳 -> 兼容重复调用stop的场景
                return;
            }
            taskCost.put(taskName, System.currentTimeMillis() - start);
        }

        public void prettyPrint() {
            StringBuilder sb = new StringBuilder();
            sb.append('\n');
            long totalCost = taskCost.values().stream().reduce(0L, Long::sum);
            sb.append("TraceWatch '").append(taskName).append("': running time = ").append(totalCost).append(" ms");
            sb.append('\n');
            if (taskCost.isEmpty()) {
                sb.append("No task info kept");
            } else {
                sb.append("---------------------------------------------\n");
                sb.append("ms         %     Task name\n");
                sb.append("---------------------------------------------\n");
                NumberFormat pf = NumberFormat.getPercentInstance();
                pf.setMinimumIntegerDigits(2);
                pf.setMinimumFractionDigits(2);
                pf.setGroupingUsed(false);
                for (Map.Entry<String, Long> entry : taskCost.entrySet()) {
                    sb.append(entry.getValue()).append("\t\t");
                    sb.append(pf.format(entry.getValue() / (double) totalCost)).append("\t\t");
                    sb.append(entry.getKey()).append("\n");
                }
            }

            System.out.printf("\n---------------------\n%s\n--------------------\n%n", sb);
        }

    }

    private static Random random = new Random();

    /**
     * 随机睡眠一段时间
     *
     * @param max
     */
    private static void randSleep(String task, int max) {
        int sleepMillSecond = random.nextInt(max);
        try {
            System.out.println(task + "==> 随机休眠 " + sleepMillSecond + "ms");
            Thread.sleep(sleepMillSecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // region 测试
    @Test
    public void testCost() throws InterruptedException {
        TraceWatch traceWatch = new TraceWatch();

        traceWatch.start("task1");
        randSleep("task1", 200);
        traceWatch.stop("task1");

        new Thread(() -> {
            traceWatch.start("task2");
            randSleep("task2", 100);
            traceWatch.stop("task2");
        }).start();

        traceWatch.start("task3");
        randSleep("task3", 40);
        traceWatch.stop("task3");


        new Thread(() -> {
            traceWatch.start("task4");
            randSleep("task4", 100);
            traceWatch.stop("task4");
        }).start();

        Thread.sleep(100);
        traceWatch.prettyPrint();
    }

    /**
     * 问题记录：
     * 从上面的使用demo以及输出，会发现存在一些问题
     * 1. 代码的冗余度高
     * 2. 总耗时与实际不符（总耗时是每个任务的耗时加和，但是有些任务是并行执行的）
     * 3. 最终的结果输出时，得等到所有任务执行完毕，但是上面的实现无法保证这一点
     * 4. 耗时统计的代码块抛出异常时，会导致无法正确记录耗时情况（即stop方法要求业务方确保和start一起出现，一定会被调用到）
     */
    // end

}
