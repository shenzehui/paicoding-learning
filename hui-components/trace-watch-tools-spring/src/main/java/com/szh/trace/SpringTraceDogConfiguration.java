package com.szh.trace;

import com.szh.trace.aop.TraceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动注册
 */
@Configuration
public class SpringTraceDogConfiguration {

    @Bean
    public TraceAspect traceAspect() {
        return new TraceAspect();
    }

}