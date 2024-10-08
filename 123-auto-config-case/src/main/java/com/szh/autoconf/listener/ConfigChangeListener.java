package com.szh.autoconf.listener;

import com.szh.autoconf.property.SpringValueRegistry;
import com.szh.autoconf.util.SpringUtil;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听类
 */
@Component
public class ConfigChangeListener implements ApplicationListener<ConfigChangeListener.ConfigChangeEvent> {

    public static void publishConfigChangeEvent(String key) {
        SpringUtil.publishEvent(new ConfigChangeEvent(Thread.currentThread().getStackTrace()[0], key));
    }

    @Override
    public void onApplicationEvent(ConfigChangeListener.ConfigChangeEvent event) {
        SpringValueRegistry.updateValue(event.getKey());
    }

    @Getter
    public static class ConfigChangeEvent extends ApplicationEvent {

        private String key;

        public ConfigChangeEvent(Object source, String key) {
            super(source);
            this.key = key;
        }
    }
}