package com.szh.websocket.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class WsAnswerHelper {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    private static WsAnswerHelper instance;

    @PostConstruct
    public void init() {
        WsAnswerHelper.instance = this;
    }

    // 向目标地址广播消息
    public static void publish(String destination, Object msg) {
        instance.simpMessagingTemplate.convertAndSend(destination, msg);
    }

    // 给特定用户发送消息
    public static void publish(String user, String destination, Object msg) {
        instance.simpMessagingTemplate.convertAndSendToUser(user, destination, msg);
    }
}
