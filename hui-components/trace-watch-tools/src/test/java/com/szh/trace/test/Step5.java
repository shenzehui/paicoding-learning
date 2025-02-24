package com.szh.trace.test;

import com.szh.trace.async.AsyncUtil;
import org.junit.Test;

import java.io.Closeable;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

/**
 * 异步能力增强
 */
public class Step5 {
    public static class TraceRecorder implements Closeable {

        /**
         * trace记录名
         */
        private String traceName;

        /**
         * 异步任务执行的结果
         * 等待所有任务执行完毕就可以直接借助CompletableFuture.allOf().join来实现
         */
        private final List<CompletableFuture<?>> list;

        /**
         * 一个子任务的执行耗时
         */
        private Map<String, Long> cost;

        /**
         * 用于标记是否所有的任务执行完毕
         * 执行完毕之后，不在支持继续添加记录
         */
        private volatile boolean markExecuteOver;

        /**
         * 异步调用的线程池
         */
        private final ExecutorService executorService;

        public TraceRecorder() {
            this(AsyncUtil.executorService, "TraceDog");
        }

        public TraceRecorder(ExecutorService executorService, String task) {
            this.traceName = task;
            list = new CopyOnWriteArrayList<>();
            // 支持排序的耗时记录
            cost = new ConcurrentSkipListMap<>();
            start(task);
            this.executorService = executorService;
            this.markExecuteOver = false;
        }

        public void start(String taskName) {
            if (markExecuteOver) {
                System.out.println("所有耗时任务已结束，忽略：" + taskName);
                return;
            }
            cost.put(taskName, System.currentTimeMillis());
        }

        public void end(String taskName) {
            long now = System.currentTimeMillis();
            long last = cost.getOrDefault(taskName, now);
            if (last >= now / 1000) {
                // 之前存储的是时间戳，现在需要更新为执行耗时 ms 单位
                cost.put(taskName, now - last);
            }
        }

        /**
         * 封装一下执行业务逻辑，记录耗时时间
         *
         * @param run  执行的具体业务逻辑
         * @param name 任务名
         * @return
         */
        private Runnable runWithTime(Runnable run, String name) {
            return () -> {
                start(name);
                try {
                    run.run();
                } finally {
                    end(name);
                }
            };
        }

        /**
         * 封装一下执行业务逻辑，记录耗时时间
         *
         * @param call 执行的具体业务逻辑
         * @param name 任务名
         * @return 返回结果
         */
        private <T> Supplier<T> supplyWithTime(Supplier<T> call, String name) {
            return () -> {
                start(name);
                try {
                    return call.get();
                } finally {
                    end(name);
                }
            };
        }

        /**
         * 同步执行，待返回结果
         *
         * @param supplier 执行任务
         * @param name     耗时标识
         * @param <T>      返回类型
         * @return 任务的执行返回结果
         */
        public <T> T sync(Supplier<T> supplier, String name) {
            // 直接执行代码块，并同步返回结果
            return supplyWithTime(supplier, name).get();
        }


        /**
         * 同步执行，无返回结果
         *
         * @param run  执行任务
         * @param name 耗时标识
         * @return
         */
        public void sync(Runnable run, String name) {
            // 直接执行代码块
            runWithTime(run, name).run();
        }

        /**
         * 异步执行，带返回结果
         *
         * @param supplier 执行任务
         * @param name     耗时标识
         * @return
         */
        public <T> CompletableFuture<T> async(Supplier<T> supplier, String name) {
            CompletableFuture<T> res = CompletableFuture.supplyAsync(supplyWithTime(supplier, name + "(异步)"), this.executorService);
            list.add(res);
            return res;
        }

        /**
         * 异步执行，无返回结果
         *
         * @param run  执行任务
         * @param name 耗时标识
         * @return
         */
        public CompletableFuture<Void> async(Runnable run, String name) {
            // 添加一个标识，区分同步执行与异步执行
            // 异步任务的执行，在整体的耗时占比只能作为参考
            CompletableFuture<Void> future = CompletableFuture.runAsync(runWithTime(run, name + "(异步)"), this.executorService);
            list.add(future);
            return future;
        }

        /**
         * 等待所有耗时任务结束
         */
        public TraceRecorder allExecuteOver() {
            if (!list.isEmpty()) {
                CompletableFuture.allOf(list.toArray(new CompletableFuture[]{})).join();
            }
            //记录整体结束
            end(this.traceName);
            this.markExecuteOver = true;
            return this;
        }

        public Map<String, Long> prettyPrint() {
            // 在格式化输出时，要求所有任务执行完毕
            if (!this.markExecuteOver) {
                this.allExecuteOver();
            }

            StringBuilder sb = new StringBuilder();
            sb.append('\n');
            long totalCost = cost.remove(traceName);
            sb.append("TraceWatch '").append(traceName).append("': running time = ").append(totalCost).append(" ms");
            sb.append('\n');
            if (cost.isEmpty()) {
                sb.append("No task info kept");
            } else {
                sb.append("---------------------------------------------\n");
                sb.append("ms         %     Task name\n");
                sb.append("---------------------------------------------\n");
                NumberFormat pf = NumberFormat.getPercentInstance();
                pf.setMinimumIntegerDigits(2);
                pf.setMinimumFractionDigits(2);
                pf.setGroupingUsed(false);
                for (Map.Entry<String, Long> entry : cost.entrySet()) {
                    sb.append(entry.getValue()).append("\t\t");
                    sb.append(pf.format(entry.getValue() / (double) totalCost)).append("\t\t");
                    sb.append(entry.getKey()).append("\n");
                }
            }

            System.out.printf("\n---------------------\n%s\n--------------------\n%n", sb);
            return cost;
        }

        @Override
        public void close() {
            // 做一个兜底，避免业务侧没有手动结束，导致异步任务没有执行完就提前返回结果
            this.allExecuteOver().prettyPrint();
        }
    }

    private static Random random = new Random();

    /**
     * 随机睡眠一段时间
     *
     * @param max
     */
    private static void randSleep(String task, int max) {
        randSleepAndRes(task, max);
    }

    private static int randSleepAndRes(String task, int max) {
        int sleepMillSecond = random.nextInt(max);
        try {
            System.out.println(task + "==> 随机休眠 " + sleepMillSecond + "ms");
            Thread.sleep(sleepMillSecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return sleepMillSecond;
    }


    // region 测试
    @Test
    public void testCost2() {
        try (TraceRecorder recorder = new TraceRecorder()) {
            randSleep("前置", 20);
            recorder.sync(() -> randSleep("task1", 200), "task1");
            recorder.async(() -> randSleep("task2", 100), "task2");
            recorder.sync(() -> randSleep("task3", 40), "task3");
            recorder.async(() -> randSleep("task4", 100), "task4");
        }
    }

    @Test
    public void testCost4() {
        for (int i = 0; i < 10; i++) {
            testCost();
        }
    }


    @Test
    public void testCost() {
        try (TraceRecorder recorder = new TraceRecorder()) {
            randSleep("前置", 20);
            int f1 = recorder.sync(() -> randSleepAndRes("task1", 200), "task1");
            CompletableFuture<Integer> f2 = recorder.async(() -> randSleepAndRes("task2", f1), "task2");
            recorder.sync(() -> randSleep("task3", 40), "task3");
            recorder.async(() -> randSleep("task4", 100), "task4");
            System.out.println("打印f2的结果 -> " + f2.join());
        }
    }
    // end

}
