package com.szh.trace.test;

import com.szh.trace.EnableTraceWatchDog;
import com.szh.trace.test.service.Index;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@ComponentScan("com.szh.trace.test")
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAspectJAutoProxy()
@EnableTraceWatchDog
public class BasicDemo {

    @Autowired
    private Index index;

    @Test
    public void testIndex() {
        Map map = index.buildIndexVo();
        System.out.println(map);
    }
}