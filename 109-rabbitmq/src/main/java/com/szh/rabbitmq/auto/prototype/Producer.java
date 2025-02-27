package com.szh.rabbitmq.auto.prototype;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ 生产者
 * Created by szh on 2023-07-06
 *
 * @author szh
 */

public class Producer {
    public static final String QUEUE_NAME = "szh";

    public void producer() throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 连接到本地 server
        connectionFactory.setHost("192.168.88.131");
        connectionFactory.setUsername("szh");
        connectionFactory.setPassword("123456");
        // 通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        // 通过连接创建通道
        Channel channel = connection.createChannel();

        /**
         * 创建一个名为 szh 的队列，该队列非持久(RabbitMQ重启后会消失)、非独占(非仅用于此链接)、非自动删除(服务器将不再使用的队列删除)
         *
         * durable：代表是否将此队列持久化。
         * exclusive：代表是否独占，如果设置为独占队列则此队列仅对首次声明它的连接可见，并在连接断开时自动删除。
         * autoDelete：代表断开连接后是否自动删除此队列。
         * arguments：代表其他额外参数。
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String msg = "Hello，我是 szh" + LocalDateTime.now().toString();

        // 发布消息
        // 四个参数为：指定路由器，指定key，指定参数，和二进制数据内容
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes(StandardCharsets.UTF_8));

        System.out.println("生产者发送消息结束，发送内容为：" + msg);
        channel.close();
        connection.close();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Producer producer = new Producer();
        producer.producer();
    }
}
