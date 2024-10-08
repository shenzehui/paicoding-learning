package com.szh.autoconf.context;

import com.szh.autoconf.listener.ConfigChangeListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义属性配置源
 *
 * @author 沈泽辉
 * @version 1.0
 */

public class SelfConfigContext {

    private static volatile SelfConfigContext instance = new SelfConfigContext();

    public static SelfConfigContext getInstance() {
        return instance;
    }

    private Map<String, Object> cache = new ConcurrentHashMap<>();

    public Map<String, Object> getCache() {
        return cache;
    }

    private SelfConfigContext() {
        cache.put("config.type", 33);
        cache.put("config.wechat", "lenve");
        cache.put("config.github", "shenzehui.git.io");
    }

    /**
     * 更新配置
     *
     * @param key 键
     * @param val 值
     */
    public void updateConfig(String key, Object val) {
        cache.put(key, val);
        ConfigChangeListener.publishConfigChangeEvent(key);
    }

}
