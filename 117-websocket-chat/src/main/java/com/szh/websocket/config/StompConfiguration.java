package com.szh.websocket.config;

import com.szh.websocket.interceptor.AuthHandshakeInterceptor;
import com.szh.websocket.interceptor.SocketInChannelInterceptor;
import com.szh.websocket.interceptor.SocketOutChannelInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 1. 定义端点： registerStompEndpoints()
 * 2. 定义客户端与服务端通讯信息: configureMessageBroker()
 * - 配置消息代理: registry.enableSimpleBroker
 * - 配置消息转发: registry.setApplicationDestinationPrefixes，转发前缀可以是多个
 */
@Configuration
@EnableWebSocketMessageBroker
public class StompConfiguration implements WebSocketMessageBrokerConfigurer {

    /**
     * 这里定义的是客户端接收服务端消息的相关信息
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 消息代理指定了客户端订阅地址，前端订阅的就是这个路径, 接收后端发送的消息
        // 对应 index.js中的 stompClient.subscribe('/topic/hello'
        registry.enableSimpleBroker("/topic");

        // 表示配置一个或多个前缀，通过这些前缀过滤出需要被注解方法处理的消息。
        // 例如，前缀为 /app 的 destination 可以通过@MessageMapping注解的方法处理，
        // 而其他 destination （例如 /topic /queue）将被直接交给 broker 处理
        registry.setApplicationDestinationPrefixes("/app");
    }

    /**
     * 添加一个服务端点，来接收客户端的连接
     * 即客户端创建ws时，指定的地址, let socket = new WebSocket("ws://ws/hello");
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Endpoint指定了客户端建立连接时的请求地址

        // 这个端点并不是一个固定的值，最后一个{channel}是一个变量。可以理解为聊天群，不同聊天群中的信息是相互隔离的，不会出现串频的情况。
        registry.addEndpoint("/ws/chat/{channel}")
                .setHandshakeHandler(new AuthHandshakeHandler())
                // 设置拦截器，从cookie中识别登录用户
                .addInterceptors(authHandshakeInterceptor())
                .withSockJS();
    }

    @Bean
    public AuthHandshakeInterceptor authHandshakeInterceptor() {
        return new AuthHandshakeInterceptor();
    }

    /**
     * 定义接收客户端发送消息的拦截器
     *
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        // 配置线程连接参数
        registration.taskExecutor().corePoolSize(4).maxPoolSize(4).queueCapacity(100).keepAliveSeconds(60);
        // 配置拦截器
        registration.interceptors(new SocketInChannelInterceptor());
    }

    /**
     * 定义后端返回消息给客户端的拦截器
     *
     * @param registration
     */
    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.interceptors(new SocketOutChannelInterceptor());
    }
}