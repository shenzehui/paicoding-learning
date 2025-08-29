package com.szh.agent;

/**
 * VM options：
 * -javaagent:F:\paicoding-learning\133-javaAgent\java-agent-demo-03\target\java-agent-demo-03-1.0.0-SNAPSHOT.jar=testargs
 */
public class ApiTest {

    public static void main(String[] args) throws InterruptedException {
        ApiTest apiTest = new ApiTest();
        apiTest.echoHi();
    }

    private void echoHi() throws InterruptedException {
        System.out.println("hi agent");
        Thread.sleep((long) (Math.random() * 500));
    }

}
