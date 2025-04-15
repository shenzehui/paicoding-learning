package com.szh.breaker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

@RestController
@RequestMapping("/test")
public class TestBreakerController {

    /**
     * 测试超时熔断
     */
    @RequestMapping(value = "/testTimeoutBreaker", method = RequestMethod.GET)
    public String testTimeoutBreaker() throws InterruptedException {
        Thread.sleep(2000);
        return "HELLO WORLD";
    }

    /**
     * 测试异常熔断
     */
    @RequestMapping(value = "/testExceptionBreaker", method = RequestMethod.GET)
    public String testExceptionBreaker() {
        try {
            int res = 1 / 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "HELLO WORLD";
    }
}
