package com.szh.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB 动态代理
 */
public class ProxyFactory implements MethodInterceptor {
    //维护⼀个⽬标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //为⽬标对象⽣成代理对象
    public Object getProxyInstance() {
        //⼯具类
        Enhancer en = new Enhancer();
        //设置⽗类
        en.setSuperclass(target.getClass());
        //设置回调函数
        en.setCallback(this);
        //创建⼦类对象代理
        return en.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy
            proxy) throws Throwable {
        System.out.println("请问有什么可以帮到您？");
        // 执⾏⽬标对象的⽅法
        Object returnValue = method.invoke(target, args);
        System.out.println("问题已经解决啦！");
        return returnValue;
    }
}