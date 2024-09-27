package com.szh.async.controller;

import com.szh.redisson.annoation.DistributedLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class TestRedissionController {

    @GetMapping("/hello")
    @DistributedLock(key = "#param", lockTime = 10L, keyPrefix = "test-lock-")
    public String sayHello(String param) {
        System.out.println("param = " + param);
        return "Hello Everyone";
    }
}
