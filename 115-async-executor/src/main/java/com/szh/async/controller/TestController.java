package com.szh.async.controller;

import com.szh.async.annoation.AsyncExecute;
import org.springframework.web.bind.annotation.PostMapping;
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
public class TestController {


    @AsyncExecute(timeOutRsp = "#param")
    @PostMapping(value = "/testTimeOut")
    public String testTimeOut(String param) throws InterruptedException {
        Thread.sleep(4000);
        return "SUCCESS";
    }

    @AsyncExecute(timeOutRsp = "#param")
    @PostMapping(value = "/test")
    public String test(String param) throws InterruptedException {
        Thread.sleep(2000);
        return "SUCCESS";
    }
}
