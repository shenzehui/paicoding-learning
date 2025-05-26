package com.szh.spring;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 对象工厂
 * <p>
 * 核心类，初始化的时候，创建 Bean 注册器，完成资源加载
 */
@Slf4j
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private BeanRegister beanRegister;


    public BeanFactory() {
        beanRegister = new BeanRegister();
        this.beanDefinitionMap = new ResourceLoader().getResource();
    }


    /**
     * 获取 bean
     *
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        Object bean = beanRegister.getSingletonBean(beanName);
        if (bean != null) {
            log.info("走缓存，单例 Bean: {}", beanName);
            return bean;
        }
        // 根据 bean 定义，创建 bean
        return createBean(beanDefinitionMap.get(beanName));
    }


    /**
     * 创建 bean
     *
     * @param beanDefinition
     * @return
     */
    private Object createBean(BeanDefinition beanDefinition) {
        log.info("创建 bean: {}", beanDefinition.getBeanName());
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
            beanRegister.registerSingletonBean(beanDefinition.getBeanName(), bean);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return bean;
    }

}
