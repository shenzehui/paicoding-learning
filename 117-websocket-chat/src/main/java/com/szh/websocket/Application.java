package com.szh.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 服务端，像订阅了 /topic/hello 的连接端主动发送消息
     *
     * @throws IOException
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void sc1() throws IOException {
        String rspMsg = Thread.currentThread().getName() + " 自动返回 | sc1：" + LocalDateTime.now();
        // 后端主动给前端发送消息
        simpMessagingTemplate.convertAndSend("/topic/hello", rspMsg);
    }

}