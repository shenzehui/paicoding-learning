package com.szh.context.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

@Component
public class SpringUtil implements ApplicationContextAware {

    private volatile static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.context = applicationContext;
    }

    /**
     * 发布消息事件
     *
     * @param event 消息事件
     */
    public static void publishEvent(ApplicationEvent event) {
        context.publishEvent(event);
    }
}
