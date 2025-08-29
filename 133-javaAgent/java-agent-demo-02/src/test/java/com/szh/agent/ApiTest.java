package com.szh.agent;

/**
 * <p>
 * 配置监控
 * VM options：
 * -javaagent:F:\paicoding-learning\133-javaAgent\java-agent-demo-02\target\java-agent-demo-02-1.0.0-SNAPSHOT.jar=testargs
 */
public class ApiTest {

    public static void main(String[] args) {
        ApiTest apiTest = new ApiTest();
        apiTest.echoHi();
    }

    private void echoHi() {
        System.out.println("hi agent");
    }

}
