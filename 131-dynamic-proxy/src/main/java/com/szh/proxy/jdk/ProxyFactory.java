package com.szh.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理工厂
 */
public class ProxyFactory {

    // 维护一个目标对象
    private Object target;


    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 为目标对象生成代理对象
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), // 目标类的类加载器
                target.getClass().getInterfaces(), // 目标类实现的接口
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println(proxy.getClass().getName());
                        System.out.println("请问有什么可以帮到您?");
                        // 调用目标对象方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("问题已经解决啦！");

//                        if ("solve".equals(method.getName())) {
//                            proxy.getClass().getMethod("call").invoke(target, args);
//                        }

                        return returnValue;
                    }
                });

    }
}
