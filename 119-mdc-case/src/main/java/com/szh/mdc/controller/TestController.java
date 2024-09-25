package com.szh.mdc.controller;

import com.szh.mdc.annoation.MdcDot;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TestController {

    @GetMapping("/test")
    @MdcDot(bizCode = "#param")
    public String test(String param) {
        log.info(param);
        return "SUCCESS";
    }
}
