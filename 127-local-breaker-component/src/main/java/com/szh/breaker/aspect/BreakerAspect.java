package com.szh.breaker.aspect;

import com.szh.breaker.component.LocalBreakerState;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.ZoneId;

/**
 * @author shenzehui
 */
@Slf4j
@Aspect
@Component
public class BreakerAspect {

    private static final Clock CLOCK = Clock.system(ZoneId.of("GMT+8"));

    private final LocalBreakerState localBreakerState;

    public BreakerAspect(LocalBreakerState localBreakerState) {
        this.localBreakerState = localBreakerState;
    }

    @Pointcut("execution(* com.szh.breaker.controller.TestBreakerController.*(..))")
    public void breakerPointCut() {
    }

    @Around("breakerPointCut()")
    public Object beforeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!localBreakerState.isBreakerEnabled()) {
            return joinPoint.proceed();
        }
        if (localBreakerState.isBreaker()) {
            throw new RuntimeException("本地接口已熔断");
        }
        try {
            long start = CLOCK.millis();
            Object ret = joinPoint.proceed();
            long cost = CLOCK.millis() - start;
            localBreakerState.watchCost(cost);
            return ret;
        } catch (Exception e) {
            log.info("接口异常，触发异常熔断");
            localBreakerState.watchEx(e);
//            throw e;
            return null;
        }
    }
}