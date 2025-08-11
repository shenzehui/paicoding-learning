package com.szh.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

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
        new SpringApplicationBuilder(Application.class).web(WebApplicationType.SERVLET).run(args);
    }

    public static final String GLOBAL_CHANNEL = "globalChannel";

    /**
     * 服务端
     *
     * @throws IOException
     */
//    @Scheduled(cron = "0/10 * * * * ?")
    public void sc1() throws IOException {
        String rspMsg = Thread.currentThread().getName() + " 自动返回 | sc1：" + LocalDateTime.now();
        // 后端主动给前端发送消息
        simpMessagingTemplate.convertAndSend("/topic/chat/" + GLOBAL_CHANNEL, rspMsg);
    }

}