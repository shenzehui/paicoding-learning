package com.szh.breaker.component;

import com.szh.breaker.properties.BreakerProperties;
import com.szh.breaker.constant.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 本地断路器组件
 *
 * @author shenzehui
 */
@Slf4j
@Component
public class LocalBreakerState {

    /** 只有异常等待情况下才需要独立现成去处理 */
    private static final ExecutorService POOL = new ThreadPoolExecutor(
            4, 6, 20, TimeUnit.MINUTES, new ArrayBlockingQueue<>(100));

    private CountDownLatch allCount;

    private CountDownLatch exCount;

    private final BreakerProperties.Breaker breaker;

    /** 0-进行常规技术/1-半开状态/2-所有服务都直接报错返回 */
    private final AtomicInteger state = new AtomicInteger(0);

    public boolean isBreakerEnabled() {
        return this.breaker.isEnabled();
    }

    public boolean isBreaker() {
        return state.get() == 2;
    }

    public LocalBreakerState(BreakerProperties breakerProperties) {
        this.breaker = breakerProperties.getBreaker();
        init();
    }

    public void init() {
        // 11
        this.allCount = new CountDownLatch(this.breaker.getWindowInTimes() + 1);
        // 11 * 0.6 + 1 = 7
        this.exCount = new CountDownLatch(
                this.breaker.getWindowInTimes() * this.breaker.getBreakerErrorPercentage() / 100 + 1);
    }

    public void watchCost(long cost) {
        allCount.countDown();

        if (!breaker.getResCostMax().isZero()) {
            if (breaker.getResCostMax().compareTo(Duration.ofMillis(cost)) <= 0) {
                if (state.get() == 1) {
                    log.error("[" + ErrorCode.LOCAL_BREAK + "]半开耗时仍超过预期而断开", cost);
                    // 半开失败，继续检讨
                    doException();
                    return;
                } else {
                    log.info("调用超时了，耗时：{}", cost);
                    exCount.countDown();
                }
            } else {
                // 没超指标
                if (state.get() == 1) {
                    log.error("[" + ErrorCode.LOCAL_BREAK + "]半开耗时恢复正常，进入常规状态", cost);
                    // 半开检测成功，初始化状态
                    init();
                    state.set(0);
                    return;
                }
            }
        } else {
            // 0 表示没超时限制
            if (state.get() == 1) {
                log.info("半开检测成功，没超时限制，消耗了：{}", cost);
                // 半开检测成功，初始化状态
                init();
                state.set(0);
                return;
            }
        }
        log.info("总计数器：{} 异常计数器：{}", allCount.getCount(), exCount.getCount());
        check();
    }

    public void watchEx(Exception e) {
        if (state.get() == 1) {
            log.error("[" + ErrorCode.LOCAL_BREAK + "]半开仍异常而断开", e);
            // 半开失败，继续检讨
            doException();
        } else {
            allCount.countDown();
            exCount.countDown();
            log.info("总计数器：{} 异常计数器：{}", allCount.getCount(), exCount.getCount());
            check();
        }
    }

    private void check() {
        if (allCount.getCount() <= 1) {
            // 总数到了
            if (exCount.getCount() <= 1) {
                log.error("[" + ErrorCode.LOCAL_BREAK + "]根据接口异常反馈，进入断开状态");
                // 异常数也到了
                doException();
            } else {
                init();
            }
        } else {
            // 总数没到
            if (exCount.getCount() <= 1) {
                log.error("[" + ErrorCode.LOCAL_BREAK + "]根据接口异常反馈，进入断开状态");
                // 异常数到了
                doException();
            }
        }
    }

    private void doException() {
        if (state.get() == 2) {
            // 数据如果已经是2，说明有线程已经开始等待了，不用再次开辟线程处理状态
            return;
        }
        // 此时计数器应该停止计数
        state.set(2);
        POOL.execute(() -> {
            try {
                // 由于计数器停止计数，这里理论不会进入true，
                // 若计数器变为零了，则返回 true；若指定的等待时间过去了，则返回 false
                boolean flag = allCount.await(breaker.getBreakerSleep().getSeconds(), TimeUnit.SECONDS);
                if (flag) {
                    log.error("[" + ErrorCode.LOCAL_BREAK + "]计数器变更导致断开恢复，但不正常！恢复计数器");
                    // 不应该进这里，若有未知异常情况进入了，重置所有
                    init();
                    state.set(0);
                } else {
                    log.info("[" + ErrorCode.LOCAL_BREAK + "]断开恢复，开启半开状态");
                    // 超时了，进入半开状态
                    state.set(1);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}