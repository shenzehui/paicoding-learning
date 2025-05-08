package com.szh.delay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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
@Slf4j
public class TestController {

    @GetMapping("/test")
    public String test() {
        // 测试30秒延时
        DelayTaskProducer.delayTask(UUID.randomUUID().toString(), 30L, 2, "消息内容示例");
        return "SUCCESS";
    }
}
