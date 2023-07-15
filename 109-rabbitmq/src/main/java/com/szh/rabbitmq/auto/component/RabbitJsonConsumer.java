package com.szh.rabbitmq.auto.component;

import com.rabbitmq.client.Channel;
import com.szh.rabbitmq.auto.entity.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by szh on 2023-07-07
 *
 * @author szh
 */

@Slf4j
@Component("rabbitJsonConsumer")
@RabbitListener(queues = RabbitJsonConsumer.JSON_QUEUE)
public class RabbitJsonConsumer {

    public static final String JSON_QUEUE = "szh_json";

    @RabbitHandler
    public void onMessage(Client client, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        System.out.println("Message content : " + client);
        System.out.println("Message headers : " + headers);
        channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false);
        System.out.println("消息已确认");
    }
}
