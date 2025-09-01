package com.szh.agent.test;

/**
 * 线程内方法追踪
 * VM options：
 * -javaagent:F:\paicoding-learning\133-javaAgent\java-agent-demo-05\target\java-agent-demo-05-1.0.0-SNAPSHOT.jar=testargs
 */
public class ApiTest {

    public static void main(String[] args) {

        //线程一
        new Thread(() -> new ApiTest().http_lt1()).start();

        //线程二
        new Thread(() -> {
            new ApiTest().http_lt1();
        }).start();
    }


    public void http_lt1() {
        System.out.println("测试结果：hi1");
        http_lt2();
    }

    public void http_lt2() {
        System.out.println("测试结果：hi2");
        http_lt3();
    }

    public void http_lt3() {
        System.out.println("测试结果：hi3");
    }


}
