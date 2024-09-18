package com.szh.websocket.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
public class UserService {
    /**
     * 用户登录缓存：key 用户名 value sessionId
     */
    private Map<String, String> userCache;

    @Scheduled(fixedRate = 3000)
    public void autoSendMsgToUser() {
        userCache.keySet().forEach(uname -> {
            log.info("用户广播消息: {}", uname);
            WsAnswerHelper.publish(uname, "/topic/notify", String.format("【%s】当前时间: %s", uname, LocalDateTime.now()));
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}