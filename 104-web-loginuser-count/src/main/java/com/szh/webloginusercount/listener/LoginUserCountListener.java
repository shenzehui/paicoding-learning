package com.szh.webloginusercount.listener;

import com.szh.webloginusercount.service.CountService;
import com.szh.webloginusercount.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听session的创建与销毁
 * Created by szh on 2023-05-09
 *
 * @author szh
 */

@WebListener()
public class LoginUserCountListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("--------- 新增一个用户 ------- session = " + se.getSession().getId());
        HttpSessionListener.super.sessionCreated(se);
        SpringUtil.getBean(CountService.class).incr(1);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("--------- 销毁一个用户 -----------" + se.getSession().getId() + " = " + se.getSession().getAttribute("name"));
        HttpSessionListener.super.sessionDestroyed(se);
        SpringUtil.getBean(CountService.class).incr(-1);
    }
}
