package com.szh.agent.test;

/**
 * 链路追踪
 * VM options：
 * -javaagent:F:\paicoding-learning\133-javaAgent\java-agent-demo-06\target\java-agent-demo-06-1.0.0-SNAPSHOT.jar=testargs
 * <p>
 * 按需打开需要测试的模块
 * 链路监控
 * pluginGroup.add(new LinkPlugin());
 * Jvm监控
 * pluginGroup.add(new JvmPlugin());
 * <p>
 */
public class ApiTest {

    public static void main(String[] args) {

        //线程一
        new Thread(() -> new ApiTest().http_lt1("哪咤")).start();

        //线程二
        new Thread(() -> {
            new ApiTest().http_lt2("悟空");
        }).start();

    }

    public void http_lt1(String name) {
        try {
            Thread.sleep((long) (Math.random() * 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试结果：hi1 " + name);
        http_lt2(name);
    }

    public void http_lt2(String name) {
        try {
            Thread.sleep((long) (Math.random() * 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试结果：hi2 " + name);
        http_lt3(name);
    }

    public void http_lt3(String name) {
        try {
            Thread.sleep((long) (Math.random() * 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试结果：hi3 " + name);
    }

}