package com.szh.prot;

import com.szh.util.SocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * @author ASUS
 */
@SpringBootApplication
@Slf4j
public class Application {

    @Value("${server.port}")
    private Integer webPort;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @ConditionalOnExpression(value = "#{'dev'.equals(environment.getProperty('env.name'))}")
    public TomcatConnectorCustomizer tomcatConnectorCustomizer() {
        int port = SocketUtil.findAvailableTcpPort(8000, 10000, 8080);
        if (port != webPort) {
            log.info("默认8080端口号被占用，随机启用新端口号: {}", port);
            webPort = port;
        }
        return connector -> connector.setPort(webPort);
    }
}
