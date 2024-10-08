package com.szh.autoconf.controller;

import com.alibaba.fastjson2.JSON;
import com.szh.autoconf.context.SelfConfigContext;
import com.szh.autoconf.property.UserConfig;
import com.szh.autoconf.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TestController {

    @Autowired
    private UserConfig userConfig;

//    @GetMapping("/update")
//    public String update(String key, String val) {
//        SelfConfigContext.getInstance().updateConfig(key, val);
//        return "SUCCESS";
//    }
//
//    @GetMapping("/get")
//    public String getVal(String key) {
//        return SpringUtil.getConfig(key);
//    }

    @GetMapping(path = "/user")
    public UserConfig user() {
        return userConfig;
    }

    @GetMapping(path = "update")
    public String updateCache(String key, String val) {
//        selfConfigContainer.refreshConfig(key, val);
        SelfConfigContext.getInstance().updateConfig(key, val);
        return JSON.toJSONString(userConfig);
    }
}
