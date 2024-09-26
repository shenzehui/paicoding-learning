package com.szh.repeat.controller;

import com.szh.repeat.annocation.RepeatSubmit;
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

@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/hello")
    @RepeatSubmit
    public String sayHello() {

        return "SUCCESS";
    }
}
