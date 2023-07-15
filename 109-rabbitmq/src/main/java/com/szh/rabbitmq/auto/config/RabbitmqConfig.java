package com.szh.rabbitmq.auto.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Rabbitmq 配置类
 * Created by szh on 2023-07-06
 *
 * @author szh
 */

@Configuration
public class RabbitmqConfig {

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Bean
    public Queue szh() {
        // 其三个参数：durable exclusive autoDelete
        // 一般只设置一下持久化即可
        return new Queue("szh", true);
    }

    @Bean
    public Queue szhJson() {
        // 其三个参数：durable exclusive autoDelete
        // 一般只设置一下持久化即可
        return new Queue("szh_json", true);
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter(jacksonObjectMapper);
    }

    @Bean
    public DirectExchange directExchange() {
        // 三个构造函数，name duration，autoDelete
        //  一般只设置一下持久化即可
        return new DirectExchange("directExchange", false, false);
    }

}
