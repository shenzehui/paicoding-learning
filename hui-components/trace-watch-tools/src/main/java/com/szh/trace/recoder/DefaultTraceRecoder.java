package com.szh.trace.recoder;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.szh.trace.TraceWatch;
import com.szh.trace.async.AsyncUtil;
import com.szh.trace.mdc.MdcUtil;
import com.szh.trace.output.CostOutput;
import com.szh.trace.output.LogOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

public class DefaultTraceRecoder implements ITraceRecoder {

    private static final Logger log = LoggerFactory.getLogger(DefaultTraceRecoder.class);

    /**
     * trace记录名
     */
    private final String traceName;

    /**
     * 异步任务执行的结果
     */
    private final List<CompletableFuture<?>> list;
    /**
     * 一个子任务的执行耗时
     */
    private final Map<String, Long> cost;

    /**
     * 异步调度的线程池
     */
    private final ExecutorService executorService;

    /**
     * 用于标记是否所有的任务执行完毕
     * 执行完毕之后，不在支持继续添加记录
     */
    private volatile boolean markExecuteOver;

    /**
     * 结束的回调钩子
     */
    private Runnable endHook;

    /**
     * 控制是否打印日志的条件
     */
    private boolean logEnable;

    private List<CostOutput> outputList;

    public DefaultTraceRecoder() {
        this(AsyncUtil.executorService, "TraceDog", true);
    }

    public DefaultTraceRecoder(ExecutorService executorService, String task, boolean logEnable) {
        this.traceName = task;
        list = new CopyOnWriteArrayList<>();
        // 支持排序的耗时记录
        cost = new ConcurrentSkipListMap<>();
        this.executorService = TtlExecutors.getTtlExecutorService(executorService);
        this.markExecuteOver = false;
        this.logEnable = logEnable;
        this.outputList = new ArrayList<>();
        // 默认加载全局的输出重定向规则
        this.outputList.addAll(TraceWatch.getGlobalOutputStrategy());
        MdcUtil.setGlobalTraceId(MdcUtil.fetchGlobalMsgIdForTraceRecoder());
        start(task);
    }

    /**
     * 新增一个耗时重定向
     *
     * @param costOutput
     * @return
     */
    public DefaultTraceRecoder addOutput(CostOutput costOutput) {
        outputList.add(costOutput);
        return this;
    }


    @Override
    public <T> T sync(Supplier<T> supplier, String name) {
        return supplyWithTime(supplier, name).get();
    }

    @Override
    public void sync(Runnable run, String name) {
        runWithTime(run, name).run();
    }

    @Override
    public <T> CompletableFuture<T> async(Supplier<T> supplier, String name) {
        CompletableFuture<T> ans = CompletableFuture.supplyAsync(supplyWithTime(supplier, name + "(异步)"), this.executorService);
        list.add(ans);
        return ans;
    }

    @Override
    public CompletableFuture<Void> async(Runnable run, String name) {
        // 添加一个标识，区分同步执行与异步执行
        // 异步任务的执行，在整体的耗时占比只能作为参考
        CompletableFuture<Void> future = CompletableFuture.runAsync(runWithTime(run, name + "(异步)"), this.executorService);
        list.add(future);
        return future;
    }


    /**
     * 封装一下执行业务逻辑，记录耗时时间
     *
     * @param run  执行的具体业务逻辑
     * @param name 任务名
     * @return
     */
    private Runnable runWithTime(Runnable run, String name) {
        String traceId = MdcUtil.fetchGlobalMsgIdForTraceRecoder();
        return () -> {
            // 将父线程的msgId设置到当前这个执行线程
            MdcUtil.setGlobalTraceId(traceId);
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
        String traceId = MdcUtil.fetchGlobalMsgIdForTraceRecoder();
        return () -> {
            // 将父线程的msgId设置到当前这个执行线程
            MdcUtil.setGlobalTraceId(traceId);
            start(name);
            try {
                return call.get();
            } finally {
                end(name);
            }
        };
    }


    private void start(String name) {
        if (markExecuteOver) {
            // 所有任务执行完毕，不再新增
            if (log.isDebugEnabled()) {
                log.debug("allTask ExecuteOver ignore: {}", name);
            }
            return;
        }
        cost.put(name, System.currentTimeMillis());
    }

    private void end(String name) {
        long now = System.currentTimeMillis();
        long last = cost.getOrDefault(name, now);
        if (last >= now / 1000) {
            // 之前存储的是时间戳，因此我们需要更新成执行耗时 ms单位
            cost.put(name, now - last);
        }
    }

    /**
     * 等待所有的任务执行完毕
     *
     * @return
     */
    @Override
    public DefaultTraceRecoder allExecuted() {
        if (!list.isEmpty()) {
            CompletableFuture.allOf(list.toArray(new CompletableFuture[]{})).join();
        }
        // 记录整体结束
        end(this.traceName);
        this.markExecuteOver = true;
        return this;
    }

    @Override
    public Map<String, Long> prettyPrint() {
        // 格式化输出时，要求所有任务执行
        if (!this.markExecuteOver) {
            this.allExecuted();
        }

        // 根据自定义规则，对耗时输出进行处理
        outputList.forEach(o -> {
            if (!logEnable && o == LogOutput.defaultLogOutput) {
                return;
            }
            o.output(cost, traceName);
        });
        return cost;
    }

    @Override
    public void close() {
        try {
            // 做一个兜底，避免业务侧没有手动结束，导致异步任务没有执行完就提前返回结果
            this.allExecuted().prettyPrint();
        } catch (Exception e) {
            log.error("释放耗时上下文异常! {}", traceName, e);
        } finally {
            // 调用回调钩子函数
            Optional.ofNullable(endHook).ifPresent(Runnable::run);
        }
    }

    /**
     * 设置结束的回调钩子
     *
     * @param endHook 结束后回调钩子
     * @return
     */
    public DefaultTraceRecoder setEndHook(Runnable endHook) {
        this.endHook = endHook;
        return this;
    }
}
