package com.szh.context.controller;

import com.szh.context.model.event.MsgEvent;
import com.szh.context.util.SpringUtil;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequestMapping("/msg")
public class TestController {

    @GetMapping("/publish")
    public String publish(String msg) {
        SpringUtil.publishEvent(new MsgEvent<>(this, msg));
        return "SUCCESS";
    }
}
