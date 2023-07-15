package com.szh.rabbitmq.auto.component;

import com.rabbitmq.client.Channel;
import com.szh.rabbitmq.auto.prototype.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by szh on 2023-07-06
 *
 * @author szh
 */

@Component
@Slf4j
public class RabbitConsumer {

    /**
     * @param message
     * @param channel
     * @throws IOException
     * @Payload 可以单独拿到 body 部分的数据：@Payload Message message
     * @Header 可以单独使用一个注解拿到 MessageProperties的 headers 属性：@Headers Map<String,Object> headers
     */
    @RabbitListener(queues = Producer.QUEUE_NAME)
    public void onMessage(Message message, Channel channel) throws IOException {
        // 从数据上看，整个消息可以分为两部分：body和MessageProperties。
        System.out.println("Message Content:" + message);
        // 这里的 DeliveryTag 代表的是这个消息在队列中的序号，这个信息存放在MessageProperties中。
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        System.out.println("消息已确认");
    }
}
