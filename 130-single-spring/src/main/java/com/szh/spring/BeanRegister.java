package com.szh.spring;

import java.util.HashMap;
import java.util.Map;

/**
 * 对象注册器
 * <p>
 * 简化版，直接用于单例 bean 的缓存，默认所用 bean 都是单例的
 *
 * @author szh
 */
public class BeanRegister {

    // 单例 bean 的缓存
    private Map<String, Object> singletonMap = new HashMap<>(32);


    /**
     * 获取单例 Bean
     *
     * @param beanName bean名称
     * @return 单例 Bean
     */
    public Object getSingletonBean(String beanName) {
        return singletonMap.get(beanName);
    }

    /**
     * 注册单例 Bean
     *
     * @param beanName bean名称
     * @param bean     单例 Bean
     */
    public void registerSingletonBean(String beanName, Object bean) {
        if (singletonMap.containsKey(beanName)) {
            return;
        }
        singletonMap.put(beanName, bean);
    }

}
