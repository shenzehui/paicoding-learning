package com.szh.trace;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 非SpringBoot环境下，可以通过 @EnableTraceWatchDog 来开启
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SpringTraceDogConfiguration.class)
@Documented
public @interface EnableTraceWatchDog {
}