package com.szh.redisson.annoation;

import com.szh.redisson.aspect.DistributedLockAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableDistributedLock 元注解，开启分布式锁功能
 *
 * @author szh
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({DistributedLockAspect.class})
public @interface EnableDistributedLock {
}
