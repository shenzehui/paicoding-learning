package com.szh.rabbitmq.auto.component;

import com.szh.rabbitmq.auto.entity.Client;
import com.szh.rabbitmq.auto.entity.User;
import com.szh.rabbitmq.auto.prototype.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by szh on 2023-07-06
 *
 * @author szh
 */

@Component("rabbitProduce")
@Slf4j
public class RabbitProduce {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String message = "Hello，欢迎关注我。" + LocalDateTime.now();
        System.out.println("Message content : " + message);

        // 指定消息类型
        MessageProperties props = MessagePropertiesBuilder.newInstance().setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN).build();
        /**
         * send 方法是发送 byte 数组的数据的模式，这里代表消息内容的对象是 Message 对象，它的构造方法就是传入 byte 数组数据，
         * 所以我们需要把我们的数据转成 byte 数组然后构造成一个 Message 对象再进行发送。
         */
        rabbitTemplate.send(Producer.QUEUE_NAME, new Message(message.getBytes(StandardCharsets.UTF_8), props));
        System.out.println("消息发送完毕。");
    }

    /**
     * convertAndSend 方法是可以传入 POJO 对象作为参数，
     * 底层是有一个 MessageConverter 帮我们自动将数据转换成 byte 类型或 String 或序列化类型。
     */
    public void convertAndSend() {
        User user = new User();

        System.out.println("Message content : " + user);

        rabbitTemplate.convertAndSend(Producer.QUEUE_NAME, user);
        System.out.println("消息发送完毕。");
    }

    public void sendObject() {
        Client client = new Client();
        System.out.println("Message content : " + client);
        rabbitTemplate.convertAndSend(RabbitJsonConsumer.JSON_QUEUE, client);
        System.out.println("消息发送完毕。");
    }

    public void sendAndConfirm() {
        User user = new User();

        log.info("Message content : " + user);

        // CorrelationData 参数用来做消息的唯一标识
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(Producer.QUEUE_NAME, user, correlationData);
        log.info("消息发送完毕。");

        /**
         * 参数是一个匿名类，消息却仍成功或失败之后的处理都可以写在这个匿名类里面
         * 比如一条订单消息，当消息确认到达 MQ 确认之后再行入库或者修改订单的节点状态，如果消息没有成功到达 MQ 可以进行一次记录或者将订单状态修改。
         */
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("CorrelationData content : " + correlationData);
                log.info("Ack status : " + ack);
                log.info("Cause content : " + cause);
                if (ack) {
                    log.info("消息成功发送，订单入库，更改订单状态");
                } else {
                    log.info("消息发送失败：" + correlationData + ", 出现异常：" + cause);
                }
            }
        });
    }

    public void sendAndReturn() {
        User user = new User();
        log.info("Message content:" + user);

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("被退回的消息为：{}", message);
            log.info("replyCode：{}", replyCode);
            log.info("replyText：{}", replyText);
            log.info("exchange：{}", exchange);
            log.info("routingKey：{}", routingKey);
        });

        rabbitTemplate.convertAndSend("fail", user);
        log.info("消息发送完毕");
    }
}