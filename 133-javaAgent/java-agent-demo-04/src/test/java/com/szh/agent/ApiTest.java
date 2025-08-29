package com.szh.agent;

import java.util.LinkedList;
import java.util.List;

/**
 * -javaagent:F:\paicoding-learning\133-javaAgent\java-agent-demo-04\target\java-agent-demo-04-1.0.0-SNAPSHOT.jar=testargs
 */
public class ApiTest {

    public static void main(String[] args) {
        while (true) {
            List<Object> list = new LinkedList<>();
            list.add("嗨！JavaAgent");
            list.add("嗨！JavaAgent");
            list.add("嗨！JavaAgent");
        }
    }

}
