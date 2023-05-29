package com.szh.webloginusercount.controller;

import com.szh.webloginusercount.service.CountService;
import com.szh.webloginusercount.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * Created by szh on 2023-05-09
 *
 * @author szh
 */

@RestController
public class LoginController {

    @Autowired
    private CountService countService;

    /**
     * 登录返回当前在线人数
     *
     * @param uname
     * @param httpSession
     * @return
     */
    @RequestMapping(path = "/login")
    public String login(String uname, HttpSession httpSession) {
        httpSession.setAttribute("name", uname);
        System.out.println("登录成功" + uname);
        return "欢迎登录：" + uname + "，当前在线人数：" + SpringUtil.getBean(CountService.class).getOnlineCnt();
    }

    /**
     * 注销
     *
     * @param httpSession
     * @return
     */
    @RequestMapping(path = "/logout")
    public String logout(HttpSession httpSession) {
        // 注销当前用户
        httpSession.invalidate();
        return "登出成功，当前在线人数：" + countService.getOnlineCnt();
    }

    /**
     * 获取在线人数
     *
     * @param httpSession
     * @return
     */
    @RequestMapping("/online")
    public String showOnlineUser(HttpSession httpSession) {
        return httpSession.getAttribute("name") + "，当前时间为：" + LocalDateTime.now() + "在线人数" + SpringUtil.getBean(CountService.class).getOnlineCnt();
    }
}
